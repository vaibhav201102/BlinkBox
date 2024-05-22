package com.example.blinkbox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkbox.databinding.LayoutMatrixItemViewBinding

class MatrixAdapter(val count : Int, private val clickListener : (Int,Int) -> Unit) : RecyclerView.Adapter<MatrixAdapter.MatrixViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MatrixAdapter.MatrixViewHolder {
        val view = LayoutMatrixItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MatrixViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatrixAdapter.MatrixViewHolder, position: Int) {
        holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.yellow))

        val row = position / count
        val col = position % count
        holder.bind(row,col, clickListener)
    }

    override fun getItemCount(): Int = count * count

    inner class MatrixViewHolder(val binding : LayoutMatrixItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(row : Int, col : Int, clickListener: (Int, Int) -> Unit){
            itemView.setOnClickListener {
                clickListener(row,col)
            }
        }
    }
}