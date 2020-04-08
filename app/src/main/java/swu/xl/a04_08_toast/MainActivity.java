package swu.xl.a04_08_toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.temporal.ValueRange;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 默认样式
     * @param view
     */
    public void show_default(View view) {
        Toast.makeText(this, "默认样式", Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义位置
     * @param view
     */
    public void show_self_location(View view) {
        Toast toast = Toast.makeText(this, "自定义位置", Toast.LENGTH_SHORT);
        //设置位置
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    /**
     * 带图片的
     * @param view
     */
    public void show_self_pic(View view) {
        Toast toast = Toast.makeText(this, "带图片的", Toast.LENGTH_SHORT);
        //设置图片
        LinearLayout linearLayout = (LinearLayout) toast.getView();
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher_background);
        linearLayout.addView(imageView,0);//第二个参数index可以决定和文本的相对位置
        toast.show();
    }

    /**
     * 完全自定义
     * @param view
     */
    public void show_self_all(View view) {
        //加载布局
        LinearLayout inflate = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.toast_layout, null);
        //找到控件
        TextView title = inflate.findViewById(R.id.title_text);
        ImageView show = inflate.findViewById(R.id.show_img);
        TextView main = inflate.findViewById(R.id.main_text);
        //设置数据
        title.setText("这是标题");
        show.setImageResource(R.drawable.ic_launcher_background);
        main.setText("这是主要内容");
        //创建Toast
        Toast toast = new Toast(this);
        //设置位置
        toast.setGravity(Gravity.RIGHT| Gravity.BOTTOM,12,40);
        //设置持续时间
        toast.setDuration(Toast.LENGTH_LONG);
        //添加
        toast.setView(inflate);
        //显示
        toast.show();
    }

    /**
     * 其他线程
     * @param view
     */
    public void show_other_thread(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                showToast();
            }
        }).start();
    }

    //显示其他线程
    private void showToast() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "来自其他线程", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
