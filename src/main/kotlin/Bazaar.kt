//class Data
//class Repo (private val ret: Retrofit, private val dao: Dao) {
//
//    suspend fun fetchData(): List<Data> {
//        ret.fetchData()
//    }
//
//    suspend fun updateRemoteBookmark(id: Long): State {
//        ret....
//    }
//
//    suspend fun updateDatabase(id: Long, state: State) {
//        dao.....
//    }
//
//    fun getLocalBookmarks(): flow<List<Data>> {
//        dao ....
//    }
//
//}
//
//
//
//class Usecase(repo: Repo, ioDispatcher: CD) {
//
//    suspend fun updateData(id: Long): State {
//        withContext(ioDispatcher) {
//            repo.updateDatabase(id, State.Progress)
//            val res = repo.updateRemoteBookmark(id)
//            repo.updateDatabase(id, res)
//            return res
//        }
//    }
//
//    fun getBookmarks() = repo.getLocalBookmarks()
//}
//
//
//
//
//class ViewModel(private val usecase: Usecase) {
//
//    val networkResState: Observable = null
//
//
//    fun onBookmarkClick(id) {
//        scope.launch {
//            networkResState = usecase.updateData
//        }
//    }
//
//    fun getBookmarks() {
//        usecase.getBookmarks()
//            .collect()
//    }
//}