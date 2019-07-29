package com.polyhose.tabtest;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.polyhose.R;
import com.polyhose.base.BaseAdapter;
import com.polyhose.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class MaterialAdapter extends BaseAdapter<Material, MaterialAdapter.MaterialVH> {


    protected MaterialAdapter(List<Material> list) {
        super(list);
    }

    @NonNull
    @Override
    public MaterialVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_material, viewGroup, false);


        return new MaterialVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialVH materialVH, final int i) {

        Material material = getValue(i);

        materialVH.mMaterialName.setText("Material " + (i + 1));
        materialVH.mMaterialItem.setText(material.getItem());
        materialVH.mQty.setText(material.getQty());
        materialVH.mCost.setText(material.getCost());
        materialVH.mTax.setText(material.getTax());
        materialVH.mMaterialTotal.setText(material.getTotal());

        materialVH.mMaterialItem.addTextChangedListener(new MyTextWatcher() {
            @Override
            void onTextChange(String s) {
                getValue(i).setItem(s);
            }
        });

        materialVH.mQty.addTextChangedListener(new MyTextWatcher() {
            @Override
            void onTextChange(String s) {
                getValue(i).setQty(s);
            }
        });

        materialVH.mCost.addTextChangedListener(new MyTextWatcher() {
            @Override
            void onTextChange(String s) {
                getValue(i).setCost(s);
            }
        });

        materialVH.mTax.addTextChangedListener(new MyTextWatcher() {
            @Override
            void onTextChange(String s) {
                getValue(i).setTax(s);
            }
        });

        materialVH.mMaterialTotal.addTextChangedListener(new MyTextWatcher() {
            @Override
            void onTextChange(String s) {
                getValue(i).setTotal(s);
            }
        });

    }

    public static class MaterialVH extends BaseViewHolder {

        @BindView(R.id.material_name)
        TextView mMaterialName;
        @BindView(R.id.material_item)
        EditText mMaterialItem;
        @BindView(R.id.qty)
        EditText mQty;
        @BindView(R.id.cost)
        EditText mCost;
        @BindView(R.id.tax)
        EditText mTax;
        @BindView(R.id.material_total)
        EditText mMaterialTotal;


        public MaterialVH(View itemView) {
            super(itemView);
        }
    }


    abstract class MyTextWatcher implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (s.length() > 0) onTextChange(s.toString());
        }

        abstract void onTextChange(String s);

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


}
