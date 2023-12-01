package com.example.androidproject.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Intent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidproject.databinding.ItemArticleBinding
import java.text.SimpleDateFormat
import java.util.Date

class ArticleAdapter(val onItemClicked: (ArticleModel) -> Unit) : ListAdapter<ArticleModel, ArticleAdapter.ViewHolder>(diffUtil) {

    // ViewBinding을 통해 레이아웃에서 가져옴
    inner class ViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(articleModel: ArticleModel) {
            val format = SimpleDateFormat("MM월 dd일")
            val date = Date(articleModel.createdAt)

            binding.titleTextView.text = articleModel.title
            binding.dateTextView.text = format.format(date).toString()
            binding.priceTextView.text = articleModel.price

            // 판매 여부에 따라서 UI 업데이트
            if (articleModel.isSold) {
                // 판매 완료된 경우 UI 업데이트
            } else {
                // 판매 중인 경우 UI 업데이트
            }

            if (articleModel.imageUrl.isNotEmpty()) {
                Glide.with(binding.thumbnailImageView)
                    .load(articleModel.imageUrl)
                    .into(binding.thumbnailImageView)
            }

            binding.root.setOnClickListener{
                // 판매글 수정 화면으로 이동. 여기서 EditArticleActivity는 판매글 수정 화면을 담당하는 액티비티
                val intent = Intent(binding.root.context, EditArticleActivity::class.java)
                intent.putExtra("articleModel", articleModel)  // 수정할 판매글 데이터 전달
                binding.root.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ArticleModel>() {

            // 현재 노출되고 있는 아이템과 새로운 아이템이 같은지 확인 ㅡ, 새로운 아이템이 들어오면 호출됨
            // 일반적으로 키값을 통해 구분하게 됨
            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem.createdAt == newItem.createdAt
            }

            // 현재 아이템과 새로운 아이탬의 = 여부를 확인
            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
