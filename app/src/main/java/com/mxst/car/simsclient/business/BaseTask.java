package com.mxst.car.simsclient.business;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.LoginActivity;
import com.mxst.car.simsclient.layout.NetNotConnetDialog;
import com.mxst.car.simsclient.task.LoadingDialog;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.SizeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;


/**
 * BaseTask 异步任务基类
 *
 * @param <E>
 * @param <T>
 * @author 杨建洪
 */
public abstract class BaseTask<E, T> extends RequestCallBack<T> {
    //private final static String tag = BaseTask.class.getName();
    protected Context context;
    protected Dialog loadMask;
    protected HttpUtils httpUtils;
    private Handler mHandler;
    private static NetNotConnetDialog netDialog;
    public E result;
    //	Class beanType;
    private HttpHandler<E> handler;

    public BaseTask(Context context) {
        this.context = context;
        httpUtils = new HttpUtils();
    /*	ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        java.lang.reflect.Type[] params = ((ParameterizedType)parameterizedType.getActualTypeArguments()[0]).getActualTypeArguments();
		beanType = (Class)params[0];*/
    }

    public BaseTask(Context context, String loadingMsg) {
        this(context);
        loadMask = new LoadingDialog(this.context, R.style.ThemeDialogFrameless, loadingMsg);
        loadMask.show();
        // 点击后退按钮能取消dialog
        this.loadMask.setCancelable(true);
        // 点击空白地方不能取消dialog
        this.loadMask.setCanceledOnTouchOutside(false);
        this.loadMask.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface arg0) {
                BaseTask.this.doCancel();
            }
        });
    }

    public BaseTask(Context context, int msgResId) {
        this(context);
        loadMask = new LoadingDialog(this.context, R.style.ThemeDialogFrameless, this.context.getString(msgResId));
        loadMask.show();
        // 点击后退按钮能取消dialog
        this.loadMask.setCancelable(true);
        // 点击空白地方不能取消dialog
        this.loadMask.setCanceledOnTouchOutside(false);
        this.loadMask.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface arg0) {
                BaseTask.this.doCancel();
            }
        });
    }

    public void doCancel() {
        if (loadMask != null && loadMask.isShowing()) {
            loadMask.dismiss();
        }
    }

    public void requestByPost(String url, RequestParams params) {
        if (!TextUtils.isEmpty(Constant.AUTHENTICATION_TOKEN)) {
            if (params == null) {
                params = new RequestParams();
            }
            params.addQueryStringParameter("authenticationToken", Constant.AUTHENTICATION_TOKEN);

        }
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(ResponseInfo<T> responseInfo) {
        try {
            JSONObject jsonObject = new JSONObject(responseInfo.result.toString());
            result = (E) new JsonResult(jsonObject, setTypeToken().getType()) {
            };
            doCancel();
            if (jsonObject.has("errorCode") && jsonObject.optString("errorCode").equals("000002") &&
                    Constant.isLoginState == false) {
                Intent intent = new Intent(context, LoginActivity.class);
                intent.putExtra("reLogin", true);
                ((Activity) context).startActivityForResult(intent, 1);
                Constant.isLoginState = true;
                Constant.AUTHENTICATION_TOKEN = "";
            } else if (jsonObject.has("errorCode") && jsonObject.optString("errorCode").equals("000002")) {
                Constant.AUTHENTICATION_TOKEN = "";
                return;
            } else {
                onSuccess();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public abstract TypeToken setTypeToken();

    @SuppressLint("HandlerLeak")
    @Override
    public void onFailure(HttpException error, String msg) {
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        if (wifi | internet) {
            if (error.getExceptionCode() == 0) {
                Toast.makeText(context, "链接超时，请稍后再试！", Toast.LENGTH_SHORT).show();
            } else if (error.getExceptionCode() == 500) {
                Toast.makeText(context, "服务器处理异常，请稍后再试！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "网络访问出错，请稍后再试！", Toast.LENGTH_SHORT).show();
            }
        } else if (netDialog == null || (netDialog != null && !netDialog.isShowing())) {
            netDialog = NetNotConnetDialog.getInsance(context);
            netDialog.show();
            Window win = netDialog.getWindow();
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.FILL_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.y = getY();
            win.setAttributes(lp);
            DelayCloseController control = new DelayCloseController();
            control.setCloseFlags(1);             //设置信息标志位
            control.timer.schedule(control, 2000);
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 1) {
                        netDialog.dismiss();  //关闭对话框
                    }
                }
            };
        }
        doCancel();
    }


    class DelayCloseController extends TimerTask {
        private Timer timer = new Timer();
        private int actionFlags = 0;//标志位参数

        public void setCloseFlags(int flag) {
            actionFlags = flag;
        }

        @Override
        public void run() {
            Message messageFinish = new Message();
            messageFinish.what = actionFlags;
            mHandler.sendMessage(messageFinish);
        }
    }

    private int getY() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightPixels = dm.heightPixels;
        int distanceHeight = SizeUtils.dip2px(context, 25);
        int result = SizeUtils.px2dip(context, heightPixels) / 2 - distanceHeight;
        return SizeUtils.dip2px(context, result);
    }

    public abstract void onSuccess();
}
