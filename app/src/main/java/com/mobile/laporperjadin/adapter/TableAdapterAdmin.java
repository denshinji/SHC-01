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

import com.mobile.laporperjadin.DetailPengajuan;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.admin.DetailPengajuanAdmin;
import com.mobile.laporperjadin.model.Pengajuan;
import com.mobile.laporperjadin.model.PengajuanAdmin;

import java.util.List;

public class TableAdapterAdmin extends RecyclerView.Adapter {
    private Context mContext;
    private List<PengajuanAdmin> pengajuanAdminList;

    public TableAdapterAdmin(List<PengajuanAdmin> movieList, Context context) {
        this.pengajuanAdminList = movieList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.pengajuan_items_admin, parent, false);

        return new RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtId.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtKotaTujuan.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtInfo.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtId.setTextColor(Color.WHITE);
            rowViewHolder.txtNo.setTextColor(Color.WHITE);
            rowViewHolder.txtKotaTujuan.setTextColor(Color.WHITE);
            rowViewHolder.txtInfo.setTextColor(Color.WHITE);

            rowViewHolder.txtNo.setText("No");
            rowViewHolder.txtId.setText("ID");
            rowViewHolder.txtKotaTujuan.setText("Nama");
            rowViewHolder.txtInfo.setText("Info");
        } else {
            PengajuanAdmin modal = pengajuanAdminList.get(rowPos - 1);

            rowViewHolder.txtId.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtKotaTujuan.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtInfo.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtId.setTextColor(Color.BLACK);
            rowViewHolder.txtNo.setTextColor(Color.BLACK);
            rowViewHolder.txtKotaTujuan.setTextColor(Color.BLACK);
            rowViewHolder.txtInfo.setTextColor(Color.BLACK);

            rowViewHolder.txtId.setText(modal.getId() + "");
            rowViewHolder.txtNo.setText(modal.getNo() + "");
            rowViewHolder.txtKotaTujuan.setText(modal.getNama());
            rowViewHolder.txtInfo.setText("Detail");
            rowViewHolder.txtInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailPengajuanAdmin.class);
                    intent.putExtra(DetailPengajuanAdmin.PENGAJUAN_ADMIN_KEY,pengajuanAdminList.get(position-1));
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pengajuanAdminList.size() + 1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtKotaTujuan;
        TextView txtInfo;
        TextView txtNo;

        RowViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtKotaTujuan = itemView.findViewById(R.id.txtNamaPengaju);
            txtInfo = itemView.findViewById(R.id.txtInfo);
            txtNo = itemView.findViewById(R.id.txtNo);
        }
    }
}
