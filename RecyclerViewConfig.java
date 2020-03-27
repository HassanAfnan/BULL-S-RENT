package change.com.animationwithsplash;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewConfig {
    private Context mContext;
    private BookAdapter mBookAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Information> info, List<String> keys){
        mContext = context;
        mBookAdapter = new BookAdapter(info,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBookAdapter);
    }

    class BookItemView extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mAuthor;
        private TextView misbn;
        private TextView mCatagory;

        private String keys;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.list_items,parent,false));

            mTitle = (TextView) itemView.findViewById(R.id.title);
            mAuthor = (TextView) itemView.findViewById(R.id.author);
            misbn =  (TextView) itemView.findViewById(R.id.isbn);
            mCatagory = (TextView) itemView.findViewById(R.id.category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,Detail.class);
                    intent.putExtra("key",keys);
                    intent.putExtra("Name",mAuthor.getText().toString());
                    intent.putExtra("Car",mTitle.getText().toString());
                    intent.putExtra("Model",misbn.getText().toString());
                    intent.putExtra("Rent",mCatagory.getText().toString());
                    mContext.startActivity(intent);
                }
            });
        }

        public void Bind(Information info,String keys){
            mTitle.setText(info.getName());
            mAuthor.setText(info.getCar());
            misbn.setText(info.getModel());
            mCatagory.setText(info.getRent());
            this.keys = keys;
        }
    }
    class BookAdapter extends RecyclerView.Adapter<BookItemView> {
        private List<Information> mBookList;
        private List<String> mKeys;

        public BookAdapter(List<Information> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.Bind(mBookList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }

    }
}
