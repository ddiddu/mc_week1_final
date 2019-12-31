package com.example.mc_week1_final;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicView> implements Filterable {

    private ArrayList<MusicItem> musicDataList;
    private ArrayList<MusicItem> filteredList;
    private Context mContext;

    public MusicAdapter(Context context, ArrayList<MusicItem> list) {
        mContext = context;
        musicDataList = list;
        filteredList = list;
    }

    // View Holder 클래스
    public class MusicView extends RecyclerView.ViewHolder {
        ImageView imageMusic;
        TextView musicTitle, musicName;

        public MusicView(@NonNull View itemView) {
            super(itemView);

            imageMusic = (ImageView) itemView.findViewById(R.id.image_music);
            musicTitle = (TextView) itemView.findViewById(R.id.music_title);
            musicName = (TextView) itemView.findViewById(R.id.music_name);
        }
    }

    @NonNull
    @Override
    public MusicView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_music, parent, false);

        return new MusicView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicView holder, int position) {
        MusicItem item = filteredList.get(position);

        holder.musicTitle.setText(item.getTitle());
        holder.musicName.setText(item.getArtist());

        // album_id로부터 사진 불러오기 (albumart)
        Bitmap album_image = getAlbumImage(mContext, Integer.parseInt((item.getAlbum_id())),170);
        if(album_image != null) {
            holder.imageMusic.setImageBitmap(album_image);
        }
        else {    // 이미지 없을 경우
            holder.imageMusic.setImageResource(R.drawable.no_album_img);
        }
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }


    // album_id로 앨범 사진 불러오기
    private static final BitmapFactory.Options options = new BitmapFactory.Options();

    private static Bitmap getAlbumImage(Context context, int album_id, int MAX_IMAGE_SIZE) {

        ContentResolver res = context.getContentResolver();
        Uri uri = Uri.parse("content://media/external/audio/albumart/" + album_id);
        if (uri != null) {
            ParcelFileDescriptor fd = null;
            try {
                fd = res.openFileDescriptor(uri, "r");

                //크기를 얻어오기 위한옵션 ,
                //inJustDecodeBounds값이 true로 설정되면 decoder가 bitmap object에 대해 메모리를 할당하지 않고, 따라서 bitmap을 반환하지도 않는다.
                // 다만 options fields는 값이 채워지기 때문에 Load 하려는 이미지의 크기를 포함한 정보들을 얻어올 수 있다.

                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(
                        fd.getFileDescriptor(), null, options);
                int scale = 0;
                if (options.outHeight > MAX_IMAGE_SIZE || options.outWidth > MAX_IMAGE_SIZE) {
                    scale = (int) Math.pow(2, (int) Math.round(Math.log(MAX_IMAGE_SIZE / (double) Math.max(options.outHeight, options.outWidth)) / Math.log(0.5)));
                }
                options.inJustDecodeBounds = false;
                options.inSampleSize = scale;

                Bitmap b = BitmapFactory.decodeFileDescriptor(
                        fd.getFileDescriptor(), null, options);

                if (b != null) {
                    // finally rescale to exactly the size we need
                    if (options.outWidth != MAX_IMAGE_SIZE || options.outHeight != MAX_IMAGE_SIZE) {
                        Bitmap tmp = Bitmap.createScaledBitmap(b, MAX_IMAGE_SIZE, MAX_IMAGE_SIZE, true);
                        b.recycle();
                        b = tmp;
                    }
                }
                return b;
            } catch (FileNotFoundException e) {
            } finally {
                try {
                    if (fd != null)
                        fd.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {       // performFiltering, publishResults 필수
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();      // 입력받은 값 string으로 변경
                if (charString.isEmpty()) {
                    filteredList = musicDataList;
                }             // 검색값 없으면, 전체 연락처
                else {
                    ArrayList<MusicItem> filteringList = new ArrayList<>();   // 필터링 중, 검색된 연락처 저장할 변수
                    for (MusicItem name : musicDataList) {                    // 반복문으로 전체 필터 체크
                        if (name.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteringList.add(name);
                        }
                    }
                    filteredList = filteringList;       // 검색된 리스트ㄱ
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<MusicItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}

