package com.happymeerkat.motivated.notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.happymeerkat.motivated.data.models.Favorite
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.domain.repository.FavoriteRepository
import com.happymeerkat.motivated.domain.repository.QuoteRepository
import com.happymeerkat.motivated.notification.AlarmReceiver.SerializableHelper.serializable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class OnFavoritedBroadCastReceiver: BroadcastReceiver() {
    @Inject lateinit var quoteRepository: QuoteRepository
    @Inject lateinit var favoriteRepository: FavoriteRepository
    override fun onReceive(context: Context?, intent: Intent?) {
        val myScope = CoroutineScope(IO)
        myScope.launch {
            withContext(Dispatchers.IO) {
                val quote = intent?.serializable("quote") as? Quote
                if(quote != null) {
                    favoriteRepository.insertFavorite(
                        Favorite(quote.id)
                    )
                }
            }
        }
    }
}