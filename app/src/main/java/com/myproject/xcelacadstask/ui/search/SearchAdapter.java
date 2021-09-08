package com.myproject.xcelacadstask.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.myproject.xcelacadstask.R;
import com.myproject.xcelacadstask.data.model.ItemsItem;
import com.myproject.xcelacadstask.ui.player.PlayerActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

   private Activity context;
   private List<ItemsItem> itemArrayList;

    public SearchAdapter(Activity context, List<ItemsItem> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);
        return new SearchViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchViewHolder holder, int position) {
        ItemsItem itemsItem = itemArrayList.get(position);

        holder.title.setText(itemsItem.getSnippet().getTitle());
        holder.description.setText(itemsItem.getSnippet().getDescription());

        Glide.with(context)
                .load(itemsItem.getSnippet().getThumbnails().getJsonMemberDefault().getUrl())
                .into(holder.imgThumbnail);

        holder.title.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayerActivity.class);
            intent.putExtra("videoId",itemsItem.getId().getVideoId());
            intent.putExtra("title",itemsItem.getSnippet().getTitle());
            intent.putExtra("description",itemsItem.getSnippet().getDescription());
            context.startActivity(intent);
        });


        holder.imgThumbnail.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayerActivity.class);
            intent.putExtra("videoId",itemsItem.getId().getVideoId());
            intent.putExtra("title",itemsItem.getSnippet().getTitle());
            intent.putExtra("description",itemsItem.getSnippet().getDescription());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        try {
          return itemArrayList.size();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return 0;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{

        TextView title,description;
        ShapeableImageView imgThumbnail;

        public SearchViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtTitle);
            description = itemView.findViewById(R.id.txtDescription);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
        }
    }
}
