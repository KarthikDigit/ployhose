package com.aclocationtrack.tabtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class MaterialJobAdapter extends BaseAdapter<Job, MaterialJobAdapter.MaterialVH> {


    protected MaterialJobAdapter(List<Job> list) {
        super(list);
    }

    @NonNull
    @Override
    public MaterialVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_material_job, viewGroup, false);


        return new MaterialVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialVH vh, final int i) {

        Job material = getValue(i);

        vh.mMaterialJobName.setText(material.getJob() + "");
        vh.mMaterialJobDescription.setText(material.getDescription() + "");

        vh.update(material.getMaterialList());


    }

    public static class MaterialVH extends BaseViewHolder {

        @BindView(R.id.material_job_name)
        TextView mMaterialJobName;
        @BindView(R.id.material_job_description)
        TextView mMaterialJobDescription;
        @BindView(R.id.materialView)
        RecyclerView mMaterialView;
        private MaterialJobViewAdapter materialJobViewAdapter;


        public MaterialVH(View itemView) {
            super(itemView);
            mMaterialView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            mMaterialView.setHasFixedSize(true);
        }


        public void update(List<Material> materials) {

            materialJobViewAdapter = new MaterialJobViewAdapter(materials);

            mMaterialView.setAdapter(materialJobViewAdapter);


        }
    }


}
