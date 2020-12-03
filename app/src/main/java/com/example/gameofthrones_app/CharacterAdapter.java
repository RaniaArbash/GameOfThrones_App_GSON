package com.example.gameofthrones_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.TasksViewHolder>  {

    public interface OnItemClickListener {
        void onItemClick(Character item);
    }

        private Context mCtx;
        private List<Character> characterList;
        OnItemClickListener listner;

        public CharacterAdapter(Context mCtx, List<Character> characterList) {
            this.mCtx = mCtx;
            this.characterList = characterList;
            this.listner = listner;

        }

        @Override
        public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_character, parent, false);
            return new TasksViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TasksViewHolder holder, int position) {
            Character t = characterList.get(position);
            holder.characterTextView.setText(t.getCharacterName());

            if ( t.getActorName() == null)
                holder.actorTextView.setText("No actor name");
            else
                holder.actorTextView.setText(t.getActorName());


            if (t.getCharacterImageThumb() == null)
                holder.imageText.setText("no image");
            else
                holder.imageText.setText("character image");




        }

        @Override
        public int getItemCount() {
            return characterList.size();
        }

        class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView characterTextView;

            TextView actorTextView;
            TextView imageText;
            public TasksViewHolder(View itemView) {
                super(itemView);

                characterTextView = itemView.findViewById(R.id.character);
                actorTextView = itemView.findViewById(R.id.actor);
                imageText = itemView.findViewById(R.id.image);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Character character = characterList.get(getAdapterPosition());
                listner.onItemClick(character);




            }
        }


}
