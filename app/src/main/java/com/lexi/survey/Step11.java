package com.lexi.survey;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Step11 extends AppCompatActivity {

    private String filemessage;
    private Intent intent;
    private final static String EXTRA_MESSAGE = "file";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter myAdapter;
    private  List<Bitmap> imgData;
    private  List<String> imgName;
    private DataForData aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step11);
        intent=getIntent();
        filemessage=intent.getStringExtra(EXTRA_MESSAGE);
        imgData = new ArrayList<Bitmap>();
        imgName = new ArrayList<String>();
        Resources res=getResources();
        Bitmap a= BitmapFactory.decodeResource(res,R.drawable.rihanna);
        Bitmap b= BitmapFactory.decodeResource(res,R.drawable.edsheeran);
        Bitmap c= BitmapFactory.decodeResource(res,R.drawable.taylorswift );
        imgData.add(a);
        imgData.add(b);
        imgData.add(c);
        imgName.add("rihanna");
        imgName.add("edsheeran");
        imgName.add("taylorswift");
        aa=new DataForData(imgData,imgName);
        InitRecyclerView();
    }
    private void InitRecyclerView()
    {
        recyclerView=(RecyclerView)findViewById(R.id.rectclerview);
        layoutManager=new LinearLayoutManager(Step11.this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter=new MyAdapter(aa);
        recyclerView.setAdapter(myAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(this,new RecyclerViewClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(Step11.this,"11： "+imgName.get(position),Toast.LENGTH_SHORT).show();
            filemessage=filemessage+imgName.get(position);
            intent=new Intent(Step11.this,Step12.class);
            intent.putExtra(EXTRA_MESSAGE,filemessage);
            startActivity(intent);
        }

        @Override
        public void onItemLongClick(View view, int position) {
            Toast.makeText(Step11.this,"11： "+imgName.get(position),Toast.LENGTH_SHORT).show();
            filemessage=filemessage+imgName.get(position);
            intent=new Intent(Step11.this,Step12.class);
            intent.putExtra(EXTRA_MESSAGE,filemessage);
            startActivity(intent);
        }
    }));
    }
}
class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private DataForData imgData;
    public MyAdapter(DataForData Data) {
        this.imgData=Data;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.Data.setImageBitmap(imgData.imgData.get(position));
        viewHolder.tv.setText(imgData.imgName.get(position));
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return imgData.imgName.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView Data;
        public TextView  tv;
        public ViewHolder(View view){
            super(view);
            Data = (ImageView)view.findViewById(R.id.id_imgview);
            tv=(TextView)view.findViewById(R.id.textView);
        }
    }
}
class DataForData
{
    public DataForData( List<Bitmap> bitmaps,List<String> S)
    {
        imgData=bitmaps;
        imgName=S;
    }
    public   List<Bitmap> imgData;
    public   List<String> imgName;
}
 class RecyclerViewClickListener implements RecyclerView.OnItemTouchListener {

    private int mLastDownX,mLastDownY;
    //该值记录了最小滑动距离
    private int touchSlop ;
    private OnItemClickListener mListener;
    //是否是单击事件
    private boolean isSingleTapUp = false;
    //是否是长按事件
    private boolean isLongPressUp = false;
    private boolean isMove = false;
    private long mDownTime;

    //内部接口，定义点击方法以及长按方法
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public RecyclerViewClickListener(Context context, OnItemClickListener listener){
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mListener = listener;
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()){
            /**
             *  如果是ACTION_DOWN事件，那么记录当前按下的位置，
             *  记录当前的系统时间。
             */
            case MotionEvent.ACTION_DOWN:
                mLastDownX = x;
                mLastDownY = y;
                mDownTime = System.currentTimeMillis();
                isMove = false;
                break;
            /**
             *  如果是ACTION_MOVE事件，此时根据TouchSlop判断用户在按下的时候是否滑动了，
             *  如果滑动了，那么接下来将不处理点击事件
             */
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(x - mLastDownX)>touchSlop || Math.abs(y - mLastDownY)>touchSlop){
                    isMove = true;
                }
                break;
            /**
             *  如果是ACTION_UP事件，那么根据isMove标志位来判断是否需要处理点击事件；
             *  根据系统时间的差值来判断是哪种事件，如果按下事件超过1ms，则认为是长按事件，
             *  否则是单击事件。
             */
            case MotionEvent.ACTION_UP:
                if(isMove){
                    break;
                }
                if(System.currentTimeMillis()-mDownTime > 1000){
                    isLongPressUp = true;
                }else {
                    isSingleTapUp = true;
                }
                break;
        }
        if(isSingleTapUp ){
            //根据触摸坐标来获取childView
            View childView = rv.findChildViewUnder(e.getX(),e.getY());
            isSingleTapUp = false;
            if(childView != null){
                //回调mListener#onItemClick方法
                mListener.onItemClick(childView,rv.getChildLayoutPosition(childView));
                return true;
            }
            return false;
        }
        if (isLongPressUp ){
            View childView = rv.findChildViewUnder(e.getX(),e.getY());
            isLongPressUp = false;
            if(childView != null){
                mListener.onItemLongClick(childView, rv.getChildLayoutPosition(childView));
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}