package com.szsszwl.textsender;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.szsszwl.textsender.ui.receive.ReceiveFragment;
import com.szsszwl.textsender.ui.send.SendFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView receiveBtn,sendBtn;
    TextView receiveTitle,sendTitle;
    FrameLayout container;
    Fragment receiveFragment=null,sendFragment=null;
    private List<Fragment> fragmentList = new ArrayList<>();
    private int currentId = 0;
    private static final String SAVED_CURRENT_ID = "currentId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        receiveBtn = findViewById(R.id.receive_section);
        sendBtn = findViewById(R.id.send_section);
        container = findViewById(R.id.container);
        receiveTitle = findViewById(R.id.receive_title);
        sendTitle = findViewById(R.id.send_title);

        receiveBtn.setOnClickListener(this);
        sendBtn.setOnClickListener(this);
        receiveTitle.setOnClickListener(this);
        sendTitle.setOnClickListener(this);
        sendBtn.setSelected(true);
        sendTitle.setSelected(true);
        initFragments(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_section:
            case R.id.send_title:
                receiveBtn.setSelected(false);
                receiveTitle.setSelected(false);
                sendBtn.setSelected(true);
                sendTitle.setSelected(true);
                switchFragment(0);
                break;
            case R.id.receive_title:
            case R.id.receive_section:
                receiveBtn.setSelected(true);
                receiveTitle.setSelected(true);
                sendBtn.setSelected(false);
                sendTitle.setSelected(false);
                switchFragment(1);
                break;
        }
    }

    private void switchFragment(int index){
        if(index==0){
            if(sendFragment==null){
                sendFragment=new SendFragment();
            }
            addFragment(sendFragment,SendFragment.class.getName());
            showFragment(sendFragment);
        }else if(index==1){
            if(receiveFragment==null){
                receiveFragment=new ReceiveFragment();
            }
            addFragment(receiveFragment,ReceiveFragment.class.getName());
            showFragment(receiveFragment);
        }
    }


    private void initFragments(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            /*获取保存的fragment  没有的话返回null*/
            receiveFragment = getSupportFragmentManager().getFragment(savedInstanceState,ReceiveFragment.class.getName());
            sendFragment = getSupportFragmentManager().getFragment(savedInstanceState,SendFragment.class.getName());
            addToList(receiveFragment);
            addToList(sendFragment);
            int cachedId = savedInstanceState.getInt(SAVED_CURRENT_ID, 0);
            if(cachedId >= 0 && cachedId <= 4) {
                currentId = cachedId;
            }
            switchFragment(currentId);
        }else {
            switchFragment(0);
        }
    }

    private void addToList(Fragment fragment) {
        if (fragment != null) {
            fragmentList.add(fragment);
        }
    }

    /*添加fragment*/
    private void addFragment(Fragment fragment,String tag) {
        /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
        if (!fragment.isAdded()&&null==getSupportFragmentManager().findFragmentByTag(tag)) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            //commit方法是异步的，调用后不会立即执行，而是放到UI任务队列中
            transaction.add(R.id.container, fragment,tag).commit();

            //让commit动作立即执行
            getSupportFragmentManager().executePendingTransactions();

            /*添加到 fragmentList*/
            fragmentList.add(fragment);
        }
    }

    /*显示fragment*/
    private void showFragment(Fragment fragment) {
        for (Fragment frag : fragmentList) {
            if (frag != fragment) {
                /*先隐藏其他fragment*/
                getSupportFragmentManager().beginTransaction().hide(frag).commit();
            }
        }
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        if (receiveFragment != null) {
            getSupportFragmentManager().putFragment(outState,ReceiveFragment.class.getName(), receiveFragment);
        }
        if (sendFragment != null) {
            getSupportFragmentManager().putFragment(outState, SendFragment.class.getName(), sendFragment);
        }
        outState.putInt(SAVED_CURRENT_ID, currentId);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
