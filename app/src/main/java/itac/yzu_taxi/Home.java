package itac.yzu_taxi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.LinearLayout;


/**
 * Created by ChengYuShao on 15/5/15.
 * 開始頁面
 */
public class Home extends ActionBarActivity {

    private LinearLayout linearLayout;
    private Handler mHandler;
    private Thread mThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.layout_home);

        linearLayout = (LinearLayout) this.findViewById(R.id.home_LinearLayout);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1){
                    finishStartPage();//當回傳Message後，執行方選
                }
            }
        };

        linearLayout.setOnClickListener(new View.OnClickListener() {//設定Listene監聽點擊頁面
            @Override
            public void onClick(View view) {
                finishStartPage();//關閉開始頁面
            }
        });
        threadTimer();//設定倒數計時器
    }

    private void threadTimer(){//倒數兩秒，兩秒後進入主頁面
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);//暫停兩秒
                    Message message = new Message();
                    message.what = 1;
                    mHandler.sendMessage(message);//發送Messag給Handler
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        mThread.start();
    }

    private void finishStartPage(){
        finish();//關閉此頁面
        overridePendingTransition(0, R.anim.top_out);//轉場動畫：由下往上拉
        mThread.interrupt();
        mThread = null;//Threa結束處理
    }
}
