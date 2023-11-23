package jp.ac.meijou.android.applicationdeveropment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private List<String> scheduleList;
    private OnScheduleClickListener scheduleClickListener;

    public interface OnScheduleClickListener {
        void onScheduleClick(String schedule);
    }

    public ScheduleAdapter(List<String> scheduleList, OnScheduleClickListener listener) {
        this.scheduleList = scheduleList;
        this.scheduleClickListener = listener;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        String schedule = scheduleList.get(position);
        holder.bind(schedule);

        // ViewHolderがクリックされた時の処理
        //lambdaに変更
        holder.itemView.setOnClickListener(view -> {
            if (scheduleClickListener != null) {
                scheduleClickListener.onScheduleClick(schedule);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    // 外部からスケジュールリストを更新するメソッドを追加
    public void updateData(List<String> newScheduleList) {
        scheduleList.clear();
        scheduleList.addAll(newScheduleList);
        notifyDataSetChanged();
    }

    static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        private TextView scheduleTextView;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            scheduleTextView = itemView.findViewById(R.id.scheduleTextView);
        }

        public void bind(String schedule) {
            scheduleTextView.setText(schedule);
        }
    }
}