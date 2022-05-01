package uz.gita.newsappwithapi.presenter.ui.adapter

import android.content.res.Resources.getSystem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.newsappuseroom.data.model.NewsData
import uz.gita.newsappwithapi.R
import uz.gita.newsappwithapi.data.remote.response.NewsResponse
import uz.gita.newsappwithapi.databinding.ItemRvBinding

class NewsAdapter : ListAdapter<NewsData, NewsAdapter.Holder>(CategoryCallback) {

    private var onClickMoreListener: ((NewsData) -> Unit)? = null

    private object CategoryCallback : DiffUtil.ItemCallback<NewsData>() {
        override fun areItemsTheSame(oldData: NewsData, newData: NewsData): Boolean =
            oldData.title == newData.title

        override fun areContentsTheSame(oldData: NewsData, newData: NewsData): Boolean =
            oldData == newData
    }

    inner class Holder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.clickItem.setOnClickListener {
                onClickMoreListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind(): NewsData = with(binding) {
            getItem(absoluteAdapterPosition).apply {
                titleText.text = title.trim()

                Glide.with(imageNews)
                    .load(image)
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_error)
                    .into(imageNews)

                descriptionNews.text = description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return Holder(ItemRvBinding.bind(view))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }

    fun setOnCategoryClickListener(block: (NewsData) -> Unit) {
        onClickMoreListener = block
    }
}