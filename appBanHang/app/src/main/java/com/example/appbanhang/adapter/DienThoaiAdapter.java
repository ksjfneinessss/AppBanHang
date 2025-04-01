package com.example.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.R;
import com.example.appbanhang.model.SanPhamMoi;

import java.text.DecimalFormat;
import java.util.List;

public class DienThoaiAdapter extends RecyclerView.Adapter<DienThoaiAdapter.MyViewHolder> {
    Context context;
    List<SanPhamMoi> array;

    public DienThoaiAdapter(List<SanPhamMoi> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dienthoai, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPhamMoi sanPham = array.get(position);
        holder.tensp.setText(sanPham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Kiểm tra và xử lý giá sản phẩm
        try {
            Object giasp = sanPham.getGiasp();
            if (giasp != null) {
                // Đảm bảo đây là một số
                if (giasp instanceof Number) {
                    holder.giasp.setText("Giá: " + decimalFormat.format(giasp) + "đ");
                } else {
                    // Nếu là String, thử chuyển đổi sang số
                    holder.giasp.setText("Giá: " + decimalFormat.format(Double.parseDouble(giasp.toString())) + "đ");
                }
            } else {
                // Xử lý khi giá là null
                holder.giasp.setText("Giá: Liên hệ");
            }
        } catch (Exception e) {
            // Xử lý mọi ngoại lệ khác
            holder.giasp.setText("Giá: Liên hệ");
            e.printStackTrace();
        }

        holder.mota.setText(sanPham.getMota());
        Glide.with(context).load(sanPham.getHinhanh()).into(holder.hinhanh);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tensp, giasp, mota;
        ImageView hinhanh;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tensp = itemView.findViewById(R.id.itemdt_ten);
            giasp = itemView.findViewById(R.id.itemdt_gia);
            mota = itemView.findViewById(R.id.itemdt_mota);
            hinhanh = itemView.findViewById(R.id.itemdt_image);
        }
    }
}
