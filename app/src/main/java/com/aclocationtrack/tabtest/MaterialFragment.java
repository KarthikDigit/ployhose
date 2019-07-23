package com.aclocationtrack.tabtest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.aclocationtrack.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialFragment extends Fragment {


    @BindView(R.id.description)
    EditText description;
    Unbinder unbinder;
    @BindView(R.id.materialListView)
    RecyclerView materialListView;
    @BindView(R.id.add_materials)
    TextView addMaterials;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;


    //    private List<Material> materialList = new ArrayList<>();
    private MaterialAdapter adapter;

    public MaterialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_material, container, false);
        unbinder = ButterKnife.bind(this, view);

        materialListView.setLayoutManager(new LinearLayoutManager(getContext()));

//        materialListView.setHasFixedSize(true);
//
//        Material material = new Material();
//
//        materialList.add(material);

        adapter = new MaterialAdapter(new ArrayList<Material>());

        adapter.addItem(new Material());

        materialListView.setAdapter(adapter);
        materialListView.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private static final String TAG = "MaterialFragment";

    @OnClick(R.id.add_materials)
    public void onViewClicked() {


        Toast.makeText(getContext(), "Material added", Toast.LENGTH_SHORT).show();

        Material material = new Material();

        adapter.addItem(material);

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

//        materialListView.post(new Runnable() {
//            @Override
//            public void run() {
//
//
//                materialListView.smoothScrollToPosition(adapter.getList().size());
//
//            }
//        });

    }


    public Job getJob() {

        Job job = new Job();


        job.setDescription(description.getText().toString());

        job.setMaterialList(adapter.getList());

        return job;

    }


}
