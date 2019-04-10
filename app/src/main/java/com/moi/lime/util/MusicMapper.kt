package com.moi.lime.util

import com.moi.lime.db.LimeDb
import com.moi.lime.vo.*

class MusicMapper(private val recommendSongsEntity: RecommendSongsEntity, private val musicUrlsEntity: MusicUrlsEntity) {

    private fun mapMusic(): List<LimeMusic> {
        return recommendSongsEntity.recommend
                .map {
                    LimeMusic(it.name, it.id.toString(), it.duration, it.reason)
                }

    }

    private fun mapMusicUrl(): List<LimeUrl> {
        return musicUrlsEntity.data.map {
            LimeUrl(it.url, it.id.toString(), it.br.toString(), it.size.toString(), it.encodeType)
        }

    }

    private fun mapMusicAlbum(): List<LimeAlbum> {
        return recommendSongsEntity.recommend.map {
            with(it.album) {
                LimeAlbum(it.id.toString(), name, id.toString(), type, size, picId.toString(), blurPicUrl, picUrl, company)
            }
        }
    }

    private fun mapMusicArtist(): List<LimeArtist> {
        return recommendSongsEntity.recommend.flatMap { recommendItem ->
            recommendItem.artists.map {
                Pair(it, recommendItem.id)
            }
        }
                .map { artist ->
                    with(artist.first) {
                        LimeArtist(name, artist.second.toString(), id.toString(), picUrl, imgVUrl)
                    }

                }
    }

    fun saveMusic(db: LimeDb) {
        db.limeMusicDao().insertAll(*mapMusic().toTypedArray())
        db.limeUrlDao().insertAll(*mapMusicUrl().toTypedArray())
        db.limeAlbumDao().insertAll(*mapMusicAlbum().toTypedArray())
        db.limeArtistDao().insertAll(*mapMusicArtist().toTypedArray())
    }


}