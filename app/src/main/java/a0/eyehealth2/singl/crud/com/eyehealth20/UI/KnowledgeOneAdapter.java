package a0.eyehealth2.singl.crud.com.eyehealth20.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import a0.eyehealth2.singl.crud.com.eyehealth20.R;
import a0.eyehealth2.singl.crud.com.eyehealth20.entities.Knowledge;
import a0.eyehealth2.singl.crud.com.eyehealth20.introHealth.DetailActivity;

public class KnowledgeOneAdapter extends BaseAdapter {

    Context k;
    ArrayList<Knowledge> knowledges;

    public KnowledgeOneAdapter(Context k, ArrayList<Knowledge> knowledges) {
        this.k = k;
        this.knowledges = knowledges;
    }

    @Override
    public int getCount() {
        return knowledges.size();
    }

    @Override
    public Object getItem(int i) {
        return knowledges.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(k).inflate(R.layout.eye_knowledge_model,viewGroup,false);
        }

        TextView nameTxt= (TextView) view.findViewById(R.id.nameTxt);
//        TextView detailTxt= (TextView) view.findViewById(R.id.detailTxt);
        ImageView imageKnow = (ImageView) view.findViewById(R.id.imageKnow);

        Knowledge knowledge= (Knowledge) this.getItem(i);

        final String name=knowledge.getName();
        final String detail=knowledge.getDetail();
        final String image=knowledge.getImage();
        Glide.with(k).load(image).into(imageKnow);

        nameTxt.setText(name);
//        detailTxt.setText(detail);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OPEN DETAIL ACTIVITY

                openDetailActivity(name,detail,image);
            }
        });

        return view;
    }

    //open activity
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(k,DetailActivity.class);
        i.putExtra("NAME_KEY",details[0]);
        i.putExtra("DETAIL_KEY",details[1]);
        i.putExtra("IMAGE_KEY",details[2]);

        k.startActivity(i);

    }
}













