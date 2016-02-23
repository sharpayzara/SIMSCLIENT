# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

-keep public class * extends android.app.Activity      # 保持哪些类不被混淆
-keep public class * extends android.app.Application   # 保持哪些类不被混淆
-keep public class * extends android.app.Service       # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService    # 保持哪些类不被混淆
-keep class com.mxst.car.simsclient.entity.** { *; } #实体类不参与混淆
-keep class com.mxst.car.simsclient.layout.** { *; } #自定义控件不参与混淆


-keep class com.tencent.** { *; }
-keep class cn.jpush.** { *; }
-dontwarn com.google.gson.**
-dontwarn butterknife.**

####################umeng##################
#-libraryjars libs/umeng_social_sdk.jar
-keep class com.umeng.analytics.** {*;}
-dontwarn com.umeng.analytics.**

-keep class com.umeng.** { *; }
-keep class com.umeng.analytics.** { *; }
-keep class com.umeng.common.** { *; }
-keep class com.umeng.newxp.** { *; }

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}
-keep class com.umeng.**

-keep public class com.idea.fifaalarmclock.app.R$*{
    public static final int *;
}

-keep public class com.umeng.fb.ui.ThreadView {
}

-dontwarn com.umeng.**

-dontwarn org.apache.commons.**

-keep public class * extends com.umeng.**

-keep class com.umeng.** {*; }

################httpmime/httpcore##########
#-libraryjars libs/httpcore-4.1.3.jar
#-libraryjars libs/httpmime-4.1.3.jar
-keep class org.apache.http.** {*;}
-dontwarn org.apache.http.**

################xutils##################
#-libraryjars libs/xUtils-2.6.14.jar
-keep class com.lidroid.xutils.** { *; }
-keep public class * extends com.lidroid.xutils.**
-keepattributes Signature
-keepattributes *Annotation* -keep public interface com.lidroid.xutils.** {*;}
-dontwarn com.lidroid.xutils.**
-keepclasseswithmembers class com.mxst.car.simsclient.entity.**  {
         <fields>;
         <methods>;
     }

-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
    public static final android.os.Parcelable$Creator *;
}