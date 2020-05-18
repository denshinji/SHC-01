package com.mobile.laporperjadin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.admin.DetailUser;
import com.mobile.laporperjadin.model.User;

import java.util.List;

public class TableAdapterKelolaUser extends RecyclerView.Adapter {
    private Context mContext;
    private List<User> pengajuanAdminList;

    public TableAdapterKelolaUser(List<User> movieList, Context context) {
        this.pengajuanAdminList = movieList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.user_items, parent, false);

        return new UserRowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        UserRowViewHolder rowViewHolder = (UserRowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtJabatan.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtNama.setTextColor(Color.WHITE);
            rowViewHolder.txtNo.setTextColor(Color.WHITE);
            rowViewHolder.txtJabatan.setTextColor(Color.WHITE);
            rowViewHolder.txtStatus.setTextColor(Color.WHITE);

            rowViewHolder.txtNo.setText("No");
            rowViewHolder.txtNama.setText("Nama");
            rowViewHolder.txtJabatan.setText("Jabatan");
            rowViewHolder.txtStatus.setText("Status");
        } else {
            final User modal = pengajuanAdminList.get(rowPos - 1);
            String textStatus = "";
            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtJabatan.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtNama.setTextColor(Color.BLACK);
            rowViewHolder.txtNo.setTextColor(Color.BLACK);
            rowViewHolder.txtJabatan.setTextColor(Color.BLACK);
            rowViewHolder.txtStatus.setTextColor(Color.BLACK);

            if(modal.getStatus().toString().equals("1")){
                textStatus = "Aktif";
            }
            if(modal.getStatus().toString().equals("0")){
                textStatus = "Daftar";
            }

            rowViewHolder.txtNama.setText(modal.getNama() + "");
            rowViewHolder.txtNo.setText(modal.getNo() + "");
            rowViewHolder.txtJabatan.setText(modal.getJabatan());
            rowViewHolder.txtStatus.setText(textStatus+"");
            rowViewHolder.txtStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailUser.class);
                    intent.putExtra(DetailUser.USER_KEY,pengajuanAdminList.get(position-1));
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pengajuanAdminList.size() + 1;
    }

    public class UserRowViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama;
        TextView txtJabatan;
        TextView txtStatus;
        TextView txtNo;

        UserRowViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtJabatan = itemView.findViewById(R.id.txtJabatan);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtNo = itemView.findViewById(R.id.txtNoUser);
        }
    }
}
