package codility

import kotlinx.coroutines.runBlocking


class AggregateUserDataUseCase(
    private val resolveCurrentUser: suspend () -> UserEntity,
    private val fetchUserComments: suspend (UserId) -> List<CommentEntity>,
    private val fetchSuggestedFriends: suspend (UserId) -> List<FriendEntity>,
) : Closeable, AggregateUseCase {

    override suspend fun aggregateDataForCurrentUser(): AggregatedData {
        val user = resolveCurrentUser()

        val result = runBlocking {
            val commentsDeferred = async(Dispatchers.Default) {
                try {
                    withTimeoutOrNull(2000) {
                        fetchUserComments(user.id)
                    } ?: emptyList()
                } catch (e: Exception) {
                    emptyList()
                }
            }

            val friendsDeferred = async(Dispatchers.Default) {
                try {
                    withTimeoutOrNull(2000) {
                        fetchSuggestedFriends(user.id)
                    } ?: emptyList()
                } catch (e: Exception) {
                    emptyList()
                }
            }

            val comments = commentsDeferred.await()
            val friends = friendsDeferred.await()

            AggregatedData(
                user,
                comments,
                friends
            )
        }

        return result

    }

    override fun close() {
        // scope.cancel()
    }
}