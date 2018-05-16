package a0.eyehealth2.singl.crud.com.eyehealth20.introHealth;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import a0.eyehealth2.singl.crud.com.eyehealth20.R;
import a0.eyehealth2.singl.crud.com.eyehealth20.entities.Knowledge;

public class KnowledgeOneAdapter extends RecyclerView.Adapter<KnowledgeOneAdapter.KnowledgeOneViewHolder> {

    private Context mCtx;
    private List<Knowledge> knowledgeList;

    public KnowledgeOneAdapter(Context mCtx, List<Knowledge> knowledgeList) {
        this.mCtx = mCtx;
        this.knowledgeList = knowledgeList;
    }

    @Override
    public KnowledgeOneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.eye_intro_eye_activity, null);

        return new KnowledgeOneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KnowledgeOneViewHolder holder, int position) {
        Knowledge knowledge = knowledgeList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(knowledge.getImage())
                .into(holder.imageView);

        holder.textViewTitle.setText(knowledge.getName());
    }

    @Override
    public int getItemCount() {
        return knowledgeList.size();
    }

    class KnowledgeOneViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageView;

        public KnowledgeOneViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }



}
