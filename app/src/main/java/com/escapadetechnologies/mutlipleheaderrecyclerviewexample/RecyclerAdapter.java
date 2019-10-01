package com.escapadetechnologies.mutlipleheaderrecyclerviewexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<RecyclerDataList> mRecyclerDataLists;

    private static final int FOOTER_VIEW = 1;

    private static final int FIRST_LIST_ITEM_VIEW = 2;

    private static final int FIRST_LIST_ITEM_HEADER_VIEW =  3;

    private static final int SECOND_LIST_ITEM_VIEW = 4;

    private static final int SECOND_LIST_ITEM_HEADER_VIEW = 5;


    private ArrayList<RecyclerDataList> firstlist = new ArrayList<RecyclerDataList>();
    private ArrayList<RecyclerDataList> secondList = new ArrayList<RecyclerDataList>();

    public RecyclerAdapter() {
    }


    public void setFirstList(ArrayList<RecyclerDataList> firstList){
        this.firstlist = firstList;
    }

    public void setSecondList(ArrayList<RecyclerDataList> secondList){
        this.secondList = secondList;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == FOOTER_VIEW){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_view,parent,false);
            FooterViewHolder footerViewHolder = new FooterViewHolder(view);
            return footerViewHolder;
        }else if (viewType == FIRST_LIST_ITEM_VIEW){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_list_card_view,parent,false);
            FirstlistItemViewHolder viewHolder = new FirstlistItemViewHolder(view);
            return viewHolder;
        }else if (viewType == FIRST_LIST_ITEM_HEADER_VIEW){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_list_header_view,parent,false);
            FirstListHeaderViewHoler viewHoler = new FirstListHeaderViewHoler(view);
            return viewHoler;
        }else if (viewType == SECOND_LIST_ITEM_HEADER_VIEW){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_list_header_view,parent,false);
            SecondListHeaderViewHolder viewHolder = new SecondListHeaderViewHolder(view);
            return viewHolder;
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_list_card_view,parent,false);
            SecondListItemViewHolder viewHolder = new SecondListItemViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        try {

            if (holder instanceof SecondListItemViewHolder){
                SecondListItemViewHolder viewHolder = (SecondListItemViewHolder) holder;
                viewHolder.bindViewSecondList(position);
            }else if (holder instanceof FirstListHeaderViewHoler){
                FirstListHeaderViewHoler viewHoler = (FirstListHeaderViewHoler) holder;
            }else if (holder instanceof  SecondListHeaderViewHolder){
                SecondListHeaderViewHolder viewHolder = (SecondListHeaderViewHolder) holder;
            }else if (holder instanceof FirstlistItemViewHolder){
                FirstlistItemViewHolder viewHolder = (FirstlistItemViewHolder) holder;
                viewHolder.bindViewFirstList(position);
            }else if (holder instanceof FooterViewHolder){
                FooterViewHolder viewHolder = (FooterViewHolder) holder;
                viewHolder.bindViewFooter(position);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        int firstListSize = 0;
        int secondListSize = 0;

        if (secondList == null && firstlist == null) return 0;

        if (secondList != null)
            secondListSize = secondList.size();
        if (firstlist != null)
            firstListSize = firstlist.size();

        if (secondListSize > 0 && firstListSize > 0)
            return 1 + firstListSize + 1 + secondListSize + 1;   // first list header, first list size, second list header , second list size, footer
        else if (secondListSize > 0 && firstListSize == 0)
            return 1 + secondListSize + 1;                       // second list header, second list size, footer
        else if (secondListSize == 0 && firstListSize > 0)
            return 1 + firstListSize;                            // first list header , first list size
        else return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        //List items of first list
        private TextView description1;
        private TextView title1;

        //List items of Second List
        private TextView description2;
        private TextView title2;

        //Footer View
        private TextView footerTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //first list view
            title1 = itemView.findViewById(R.id.title1);
            description1 = itemView.findViewById(R.id.description1);

            //second list view
            title2 = itemView.findViewById(R.id.title2);
            description2 = itemView.findViewById(R.id.description2);

            //view of footer element
            footerTextView = itemView.findViewById(R.id.footer);

        }


        public void bindViewSecondList(int pos){

            if (firstlist == null) pos = pos - 1;
            else {
                if (firstlist.size() == 0) pos = pos - 1;
                else pos = pos - firstlist.size() - 2;
            }

            final String description = secondList.get(pos).getDescription();
            final String title = secondList.get(pos).getTitle();

            description2.setText(description);
            title2.setText(title);
        }


        public void bindViewFirstList(int pos){
            //decrease pos by 1 as there is header view now
            pos = pos - 1;

            final String description = firstlist.get(pos).getDescription();
            final String title = firstlist.get(pos).getTitle();

            title1.setText(title);
            description1.setText(description);
        }

        public void bindViewFooter(int pos){
            footerTextView.setText("This is footer");
        }
    }

    public class FooterViewHolder extends ViewHolder{

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    public class FirstListHeaderViewHoler extends ViewHolder{

        public FirstListHeaderViewHoler(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class FirstlistItemViewHolder extends ViewHolder{

        public FirstlistItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class SecondListHeaderViewHolder extends ViewHolder{

        public SecondListHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class SecondListItemViewHolder extends ViewHolder{

        public SecondListItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int firstlistSize = 0;
        int secondListSize = 0;

        if (firstlist == null && secondList == null){
            return super.getItemViewType(position);
        }

        if (secondList != null){

            secondListSize = secondList.size();
        }

        if (firstlist != null){
            firstlistSize = firstlist.size();
        }

        if (secondListSize > 0 && firstlistSize > 0){

            if (position == 0) return FIRST_LIST_ITEM_HEADER_VIEW;
            else if (position == firstlistSize + 1){
                return SECOND_LIST_ITEM_HEADER_VIEW;
            }else if (position == secondListSize + 1 + firstlistSize + 1){
                return FOOTER_VIEW;
            }
            else if (position > firstlistSize + 1){
                return SECOND_LIST_ITEM_VIEW;
            }else {
                return FIRST_LIST_ITEM_VIEW;
            }
        }else if (secondListSize > 0 && firstlistSize == 0){
                if (position == 0) return SECOND_LIST_ITEM_HEADER_VIEW;
                else return SECOND_LIST_ITEM_VIEW;
        }else if (secondListSize == 0 && firstlistSize > 0){
            if (position == 0) return FIRST_LIST_ITEM_HEADER_VIEW;
            else return FIRST_LIST_ITEM_VIEW;
        }

        return super.getItemViewType(position);

    }
}
