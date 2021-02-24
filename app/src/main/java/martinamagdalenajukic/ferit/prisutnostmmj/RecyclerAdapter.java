package martinamagdalenajukic.ferit.prisutnostmmj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NameViewHolder> {
    private List<String> studentList;
    private RemoveClickListener removeListener;

    public RecyclerAdapter(RemoveClickListener listener, List<String> studentList){
        removeListener=listener;
        this.studentList=studentList;
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new NameViewHolder(listItem, removeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        holder.setName(studentList.get(position));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvItem, tvDelete;
        private RemoveClickListener removeListener;

        public NameViewHolder(@NonNull View itemView, RemoveClickListener listener) {
            super(itemView);
            removeListener=listener;
            this.tvItem=itemView.findViewById(R.id.tvItem);
            this.tvDelete=itemView.findViewById(R.id.tvDelete);
            tvDelete.setOnClickListener(this);
        }

        public void setName(String name){ tvItem.setText(name);}

        @Override
        public void onClick(View v) { removeListener.onRemoveClick(getAdapterPosition()); }
    }

    public void addNewStudent(String student, int position){
       if(studentList.size()>=position) {
           studentList.add(position, student);
           notifyItemInserted(position);
       }
    }

    public void removeStudent(int position){
        if (studentList.size()>position){
            studentList.remove(position);
            notifyItemRemoved(position);
        }
    }
}
