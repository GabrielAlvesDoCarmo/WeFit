package com.gdsdesenvolvimento.wefit.util.result

import com.gdsdesenvolvimento.wefit.data.model.db.InfoRepo

interface RvClickItem {
    fun clickFavorite(infoRepo: InfoRepo)
}