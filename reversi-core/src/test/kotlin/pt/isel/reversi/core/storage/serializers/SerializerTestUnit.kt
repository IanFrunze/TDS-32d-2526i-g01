package pt.isel.reversi.core.storage.serializers

import pt.isel.reversi.storage.Serializer

/**
 * Test unit for serializers.
 *
 * @param U type of the data we want to convert to/from (e.g String, ..)
 * @param K type of the domain entity
 */
class SerializerTestUnit<U, K>(
    val serializer: Serializer<U, K>,
    val testData: () -> List<U>,
) {
    fun verify(testData: U) {
        serializer.run {
            assert(testData == deserialize(serialize(testData))) {
                "Failed for: $testData"
            }
        }
    }

    fun runTest() {
        for (data in testData()) {
            verify(data)
        }
    }
}