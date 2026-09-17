abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    double radius;
    Circle(double radius) { this.radius = radius; }
    double area() { return Math.PI * radius * radius; }
}

class Rectangle extends Shape {
    double width, height;
    Rectangle(double width, double height) { this.width = width; this.height = height; }
    double area() { return width * height; }
}

class Triangle extends Shape {
    double base, height;
    Triangle(double base, double height) { this.base = base; this.height = height; }
    double area() { return 0.5 * base * height; }
}

public class ShapeAreas {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(5), new Rectangle(4, 6), new Triangle(3, 8) };
        double totalArea = 0;
        double maxArea = 0;
        for (Shape s : shapes) {
            double a = s.area();
            System.out.println(s.getClass().getSimpleName() + " Area: " + a);
            totalArea += a;
            if (a > maxArea) maxArea = a;
        }
        System.out.println("Total Area: " + totalArea);
        System.out.println("Max Area: " + maxArea);
    }
}
