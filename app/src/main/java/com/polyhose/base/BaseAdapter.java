package com.polyhose.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements Filterable {

    protected List<T> list;
    protected List<T> listFiltered;
    protected OnItemClick<T> onItemClick;

    protected BaseAdapter(List<T> list) {
        this.list = new ArrayList<>();
        this.listFiltered = new ArrayList<>();
        this.list.clear();
        this.listFiltered.clear();
        this.list.addAll(list);
        this.listFiltered.addAll(list);
    }

    protected BaseAdapter(List<T> list, OnItemClick<T> onItemClick) {
        this.onItemClick = onItemClick;
        this.list = list;
        this.listFiltered = list;
        this.list.clear();
        this.listFiltered.clear();
        this.list.addAll(list);
        this.listFiltered.addAll(list);
    }

    protected BaseAdapter(OnItemClick<T> onItemClick, List<T> list) {
        this.onItemClick = onItemClick;
        this.list = list;
        this.listFiltered = list;
        this.list.clear();
        this.listFiltered.clear();
        this.list.addAll(list);
        this.listFiltered.addAll(list);
    }

//    public abstract int getLayoutById();
//
//    @NonNull
//    @Override
//    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutById(), viewGroup, false);
//
//        return (VH) new BaseViewHolder(view);
//    }


    public void remove(int position) {

        this.list.remove(position);
        this.listFiltered.remove(position);

        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    public List<T> getList() {

        return listFiltered;
    }


    public T getItem(int position) {

        return this.listFiltered.get(position);

    }

    public void update(T t, int position) {

        this.list.set(position, t);
        this.listFiltered.set(position, t);


        notifyItemChanged(position);

    }

    public void updateList(List<T> tList) {
        this.list = new ArrayList<>();
        this.list = tList;
        this.listFiltered = new ArrayList<>();
        this.listFiltered = tList;
        notifyDataSetChanged();
    }

    public void addItem(T item) {

//        this.list.remove(item);
//        this.listFiltered.remove(item);
        this.list.add(item);
        this.listFiltered.add(item);

        notifyItemInserted(this.listFiltered.size() - 1);


    }

    public void update(T t) {

        int index = this.listFiltered.indexOf(t);

        if (index != -1) {

            this.list.set(index, t);
            this.listFiltered.set(index, t);

            notifyItemChanged(index);
        }

    }

    public void addItemNoDuplicate(T item) {

        if (!(this.listFiltered.indexOf(item) > 0)) {
//        this.list.remove(item);
//        this.listFiltered.remove(item);
            this.list.add(item);
            this.listFiltered.add(item);

            notifyItemInserted(this.listFiltered.size() - 1);
        }


    }

    protected T getValue(int pos) {

        return listFiltered.get(pos);
    }


    protected List<T> getItems() {

        return listFiltered;
    }

    protected int getCount() {

        return listFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    //    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    listFiltered = list;
//                } else {
//                    List<T> filteredList = new ArrayList<>();
//                    for (T row : list) {
//
//                        // name match condition. this might differ depending on your requirement
//                        // here we are looking for name or phone number match
//                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getPhone().contains(charSequence)) {
//                            filteredList.add(row);
//                        }
//                    }
//
//                    listFiltered = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = listFiltered;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                listFiltered = (ArrayList<T>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//        ;
//    }

    protected void setText(TextView textView, String s) {

        textView.setText(s);

        if (!(s != null && s.length() > 0)) {

            textView.setVisibility(View.GONE);
        }
    }

    public void clear() {

        this.list.clear();
        this.listFiltered.clear();

        notifyDataSetChanged();

    }


    public interface OnItemClick<T> {

        void OnItemClickListener(View view, T t, int postition);

    }

}
