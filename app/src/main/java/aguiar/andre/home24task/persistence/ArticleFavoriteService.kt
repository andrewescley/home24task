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
        val exists = dao.getBySku(articleFavorite.sku) != null
        return exists
    }

    fun save(articleFavorite: ArticleFavorite): Boolean {
        val dao = DatabaseManager.getArticleFavoriteDAO()
        dao.insert(articleFavorite)
        return true
    }

}
