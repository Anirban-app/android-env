#--------------------------------------------------------------------------
# ১. গ্লোবাল অ্যান্ড্রয়েড এবং সাইজ অপ্টিমাইজেশন রুলস
#--------------------------------------------------------------------------
-dontpreverify
-repackageclasses ''
-allowaccessmodification
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# অ্যানোটেশন এবং সোর্স ফাইল এট্রিবিউটস রাখুন (ক্রাশ অ্যানালিটিক্স এবং লাইব্রেরির জন্য প্রয়োজন)
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod,SourceFile,LineNumberTable

#--------------------------------------------------------------------------
# ২. কাস্টম অ্যাপ প্যাকেজ প্রটেকশন (আপনার নিজস্ব জাভা/কোটলিন কোড প্রটেক্ট করতে)
#--------------------------------------------------------------------------
# রিলিজ বিল্ডে আপনার মেইন ডাটা মডেল এবং আর্কিটেকচার যেন রিফ্লেকশন এর কারণে ভেঙে না যায়
-keep class com.ottking.** { *; }
-keep class * implements java.io.Serializable { *; }

#--------------------------------------------------------------------------
# ৩. নেটওয়ার্কিং এবং ডেটা পার্সিং প্রটেকশন (OkHttp, Retrofit, Gson এর জন্য)
#--------------------------------------------------------------------------
# GSON এবং JSON পার্সিং লাইব্রেরির জন্য রুলস (যাতে লাইভ টিভি ডাটা প্রপারলি লোড হয়)
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**

#--------------------------------------------------------------------------
# ৪. মিডিয়া প্লেয়ার এবং স্ট্রিমিং লাইব্রেরি প্রটেকশন (ExoPlayer / Media3)
#--------------------------------------------------------------------------
# HLS (m3u8) এবং DASH (mpd) প্লেয়ারের ইন্টারনাল কোড প্রটেক্ট করার জন্য
-keep class com.google.android.exoplayer2.** { *; }
-keep class androidx.media3.** { *; }
-dontwarn com.google.android.exoplayer2.**
-dontwarn androidx.media3.**

#--------------------------------------------------------------------------
# ৫. অ্যান্ড্রয়েড এক্স এবং ম্যাটেরিয়াল ইউআই লাইব্রেরি প্রটেকশন
#--------------------------------------------------------------------------
-keep class androidx.appcompat.** { *; }
-keep class com.google.android.material.** { *; }
-dontwarn androidx.**
-dontwarn com.google.android.material.**
