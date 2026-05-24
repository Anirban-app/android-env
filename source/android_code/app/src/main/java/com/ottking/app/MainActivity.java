package com.ottking.app;

import android.app.UiModeManager;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isTvDevice = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ১. ডিভাইসটি মোবাইল নাকি স্মার্ট টিভি তা চেক করা
        isTvDevice = checkIsAndroidTV();

        if (isTvDevice) {
            // ডিভাইসটি টিভি হলে স্ক্রিন সবসময় ল্যান্ডস্কেপ (Landscape) থাকবে
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            Toast.makeText(this, "Welcome to OTTking Smart TV App", Toast.LENGTH_LONG).show();
            
            // 💡 টিভির জন্য নির্দিষ্ট কোনো কোড বা ফ্র্যাগমেন্ট থাকলে এখানে রান করুন
        } else {
            // ডিভাইসটি মোবাইল হলে ডিফল্ট পোর্ট্রেট মোড (বা আপনার ইচ্ছেমতো সেটিংস)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
            Toast.makeText(this, "Welcome to OTTking Mobile App", Toast.LENGTH_LONG).show();
            
            // 💡 মোবাইলের জন্য নির্দিষ্ট কোড এখানে রান করুন
        }
    }

    /**
     * ডিভাইসটি অ্যান্ড্রয়েড টিভি কিনা তা সনাক্ত করার ফাংশন
     */
    private boolean checkIsAndroidTV() {
        UiModeManager uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        if (uiModeManager != null) {
            return uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION;
        }
        return false;
    }

    /**
     * ২. স্মার্ট টিভির রিমোট কন্ট্রোল (D-Pad) এবং ব্যাক বাটন হ্যান্ডেল করা
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isTvDevice) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    // টিভির রিমোটে ব্যাক বাটন চাপলে কি হবে (যেমন: অ্যাপ বন্ধের আগে কনফার্মেশন ডায়ালগ)
                    // আপাতত ডিফল্ট ব্যাক অ্যাকশন রাখা হলো
                    finish();
                    return true;
                    
                case KeyEvent.KEYCODE_DPAD_CENTER:
                case KeyEvent.KEYCODE_ENTER:
                    // রিমোটের মাঝখানের OK বা সিলেক্ট বাটন চাপলে
                    return super.onKeyDown(keyCode, event);
                    
                default:
                    return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
