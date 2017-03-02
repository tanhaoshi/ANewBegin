package example.tanhao.com.jnitext;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    //D:\TextDemo\MyApplication\jnitext\src\main>javah -d jni -classpath E:\Android\sdk\platforms\android-15\android.jar;..\..\build\intermediates\classes\debug example.tanhao.com.jnitext.MainActivity
  /*
  Error:Execution failed for task ':jnitext:compileDebugNdk'.
> com.android.ide.common.process.ProcessException: org.gradle.process.internal.ExecException: Process 'command 'E:\ndk\newndk\android-ndk-r10e\ndk-build.cmd'' finished with non-zero exit value 2
   */
    static {
        System.loadLibrary("Hello-jni");
    }

     public native String getStringFromC();


    TextView jni_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        jni_text = (TextView)findViewById(R.id.jni_text);

        jni_text.setText("显示吗？"+getStringFromC());
    }
}
