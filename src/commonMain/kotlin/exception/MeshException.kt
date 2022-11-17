package exception

class MeshException(message: String): Exception(message) {
    companion object {
        fun invalidSizeForXAxis(): MeshException {
            return MeshException("Invalid size for x axis")
        }

        fun invalidSizeForYAxis(): MeshException {
            return MeshException("Invalid size for y axis")
        }

        fun coordinateStartXIsBiggerThanMesh(): MeshException {
            return MeshException("Coordinate start X s bigger than mesh")
        }

        fun coordinateStartYIsBiggerThanMesh(): MeshException {
            return MeshException("Coordinate start Y s bigger than mesh")
        }

        fun coordinateEndXIsBiggerThanMesh(): MeshException {
            return MeshException("Coordinate end X s bigger than mesh")
        }

        fun coordinateEndYIsBiggerThanMesh(): MeshException {
            return MeshException("Coordinate end Y s bigger than mesh")
        }

    }
}
