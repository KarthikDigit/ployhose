package com.aclocationtrack.tabtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class MaterialJobViewAdapter extends BaseAdapter<Material, MaterialJobViewAdapter.MaterialVH> {


    protected MaterialJobViewAdapter(List<Material> list) {
        super(list);
    }

    @NonNull
    @Override
    public MaterialVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_material_job_view, viewGroup, false);


        return new MaterialVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialVH vh, final int i) {

        Material material = getValue(i);
        vh.mMaterialQty.setText("Qty is : " + material.getQty());
        vh.mMaterialItem.setText("Item is : " + material.getItem());
        vh.mMaterialCost.setText("Cost is : " + material.getCost());


    }

    public static class MaterialVH extends BaseViewHolder {

        @BindView(R.id.material_item)
        TextView mMaterialItem;
        @BindView(R.id.material_qty)
        TextView mMaterialQty;
        @BindView(R.id.material_cost)
        TextView mMaterialCost;


        public MaterialVH(View itemView) {
            super(itemView);
        }
    }


}
