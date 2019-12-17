package aguiar.andre.home24task.persistence


object ArticleFavoriteService {
    // Retorna todos os carros favoritados
    fun getArticles(): List<ArticleFavorite> {
        val dao = DatabaseManager.getArticleFavoriteDAO()
        val articles = dao.findAll()
        return articles
    }

    fun isLike(articleFavorite: ArticleFavorite) : Boolean {
        val dao = DatabaseManager.getArticleFavoriteDAO()
        var exists = false
        var returnArticleFavorite = dao.getBySku(articleFavorite.sku)
        if(returnArticleFavorite != null){
            if (returnArticleFavorite.flagFavorite == "1"){
                exists = true
            }
        }
        return exists
    }

    fun save(articleFavorite: ArticleFavorite): Boolean {
        val dao = DatabaseManager.getArticleFavoriteDAO()
        dao.insert(articleFavorite)
        return true
    }

    fun delete():Boolean{
        val dao = DatabaseManager.getArticleFavoriteDAO()
        dao.deleteAll()
        return true
    }

}
