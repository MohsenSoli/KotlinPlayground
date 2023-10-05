package designpatterns

// Abstraction: Shape
interface Shape {
    fun draw()
}

/**
 * -------------- Wrong impl start ---------------------------------------------------
 */

// Shape and rendering technique combinations
class VectorCircle : Shape {
    override fun draw() {
        println("Drawing Circle using VectorRenderer")
    }
}

class RasterCircle : Shape {
    override fun draw() {
        println("Drawing Circle using RasterRenderer")
    }
}

class ThreeDCircle : Shape {
    override fun draw() {
        println("Drawing Circle using 3DRenderer")
    }
}

class VectorSquare : Shape {
    override fun draw() {
        println("Drawing Square using VectorRenderer")
    }
}

class RasterSquare : Shape {
    override fun draw() {
        println("Drawing Square using RasterRenderer")
    }
}

class ThreeDSquare : Shape {
    override fun draw() {
        println("Drawing Square using 3DRenderer")
    }
}

class VectorTriangle : Shape {
    override fun draw() {
        println("Drawing Triangle using VectorRenderer")
    }
}

class RasterTriangle : Shape {
    override fun draw() {
        println("Drawing Triangle using RasterRenderer")
    }
}

class ThreeDTriangle : Shape {
    override fun draw() {
        println("Drawing Triangle using 3DRenderer")
    }
}
/**
------------------------------------------------------- End of wrong. ------------------
 */

/**
Elaboration: for every shape, we need to create a subclass for each rendering technique. In this case, with 3 shapes
and 3 rendering techniques, we end up with 9 different classes. Each class handles a specific combination of a shape
and a rendering technique.
 */

/**
---------------------- Impl using bridge -----------------------
 */

/**
 *  Implementor: Renderer, the bridge.
 *  in gang of four, implementation changes for different frameworks like windows and mac
 */
interface Renderer {
    fun render(): String
}

// Concrete Implementations: VectorRenderer
class VectorRenderer : Renderer {
    override fun render(): String {
        return "Vector Renderer"
    }
}

// Concrete Implementations: RasterRenderer
class RasterRenderer : Renderer {
    override fun render(): String {
        return "Raster Renderer"
    }
}

/* -----------------------  -------------------------  ---------------------------   --------------------- */

// Concrete Implementations: Circle
class Circle(private val renderer: Renderer) : Shape {
    override fun draw() {
        println("Drawing Circle using ${renderer.render()}")
    }
}

// Concrete Implementations: Square
class Square(private val renderer: Renderer) : Shape {
    override fun draw() {
        println("Drawing Square using ${renderer.render()}")
    }
}

/**
Renderer interface (or class, depending on the implementation) acts as the bridge between the Shape abstraction and
the different rendering techniques. The Renderer interface defines the methods for rendering a shape, such as
renderCircle(), renderSquare(), etc

by using the Bridge design pattern, we decouple the shape hierarchy from the rendering hierarchy. We create separate
hierarchies for shapes and rendering techniques and combine them dynamically using composition, avoiding the need for
a large number of class combinations.
 */