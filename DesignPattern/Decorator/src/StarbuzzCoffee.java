public class StarbuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage = new Espresso(); //아무것도 넣지 않은 에스프레소 주문
        System.out.println(beverage.getDescription()
                + " $" + beverage.cost());

        Beverage beverage2 = new DarkRoast(); //DarkRoast객체 만들기
        beverage2 = new Mocha(beverage2); //Mocha로 감싸기
        beverage2 = new Mocha(beverage2); //Mocha로 한번더 감싸기
        beverage2 = new Whip(beverage2); //Whip로 감싸기
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

        Beverage beverage3 = new HouseBlend();
        beverage3 = new Mocha(beverage3);
        beverage3 = new Soy(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
    }
}
