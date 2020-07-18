package com.example.connectingworld.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.connectingworld.R;
import com.example.connectingworld.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewholder> {

    private Context context;
    private List<Result> newsData;

    public NewsAdapter(Context context, List<Result> data) {
        this.context = context;
        this.newsData = data;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_card_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final Result result = newsData.get(position);
        String title= result.getWebTitle().replaceAll("â\u0080", "'");
        holder.articleTitle.setText(title);
        holder.articleTopic.setText(result.getSectionName());
        holder.articleDate.setText(getTimeDifference(formatDate(result.getWebPublicationDate())));
        if (result.getFields() == null || result.getFields().getThumbnail() == null) {
            holder.articleImage.setVisibility(View.GONE);
        } else {
            holder.articleImage.setVisibility(View.VISIBLE);
            Glide.with(holder.articleImage.getContext()).load(result.getFields().getThumbnail()).into(holder.articleImage);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getWebUrl()));
                context.startActivity(browserIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsData.size();
    }

    public void clearAll() {
        newsData.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Result> newsList) {
        newsData.addAll(newsList);
        notifyDataSetChanged();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView articleImage;
        TextView articleTitle, articleDate, articleTopic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.itemicon);
            articleTitle = itemView.findViewById(R.id.itemtitle);
            articleDate = itemView.findViewById(R.id.itempublishddate);
            articleTopic = itemView.findViewById(R.id.itemtopic);
        }
    }

    private String formatDate(String dateStringUTC) {
        // Parse the dateString into a Date object
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'");
        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(dateStringUTC);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Initialize a SimpleDateFormat instance and configure it to provide a more readable
        // representation according to the given format, but still in UTC
        SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy  h:mm a", Locale.ENGLISH);
        String formattedDateUTC = df.format(dateObject);
        // Convert UTC into Local time
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(formattedDateUTC);
            df.setTimeZone(TimeZone.getDefault());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(date);
    }

    /**
     * Get the formatted web publication date string in milliseconds
     *
     * @param formattedDate the formatted web publication date string
     * @return the formatted web publication date in milliseconds
     */
    private static long getDateInMillis(String formattedDate) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("MMM d, yyyy  h:mm a");
        long dateInMillis;
        Date dateObject;
        try {
            dateObject = simpleDateFormat.parse(formattedDate);
            dateInMillis = dateObject.getTime();
            return dateInMillis;
        } catch (ParseException e) {
            Log.e("Problem parsing date", e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get the time difference between the current date and web publication date
     *
     * @param formattedDate the formatted web publication date string
     * @return time difference (i.e "9 hours ago")
     */
    private CharSequence getTimeDifference(String formattedDate) {
        long currentTime = System.currentTimeMillis();
        long publicationTime = getDateInMillis(formattedDate);
        return DateUtils.getRelativeTimeSpanString(publicationTime, currentTime,
                DateUtils.SECOND_IN_MILLIS);
    }
}
