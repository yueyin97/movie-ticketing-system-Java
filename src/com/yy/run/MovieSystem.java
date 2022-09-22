package com.yy.run;

import com.yy.bean.Business;
import com.yy.bean.Customer;
import com.yy.bean.Movie;
import com.yy.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MovieSystem {
    // 定义集合用户存放系统注册的用户对象信息
    public static final List<User> USERS = new ArrayList<>(); // final修饰，用户容器地址不变，但可以添加新的元素
    // 定义集合存放商家-排片信息
    public static final Map<Business, List<Movie>> INFO = new HashMap<>();

    public static final Scanner SCANNER = new Scanner(System.in);

    // 定义一个静态的用户变量（不用传参）
    public static User loginUser;

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    // 日志对象
    public static final Logger LOGGER = LoggerFactory.getLogger("MovieSystem.class");

    public static void main(String[] args) {
        showFrontPage();
    }

    private static void showFrontPage() {
        // 首页需要包含登录，商家入驻，客户注册功能
        while (true) {
            System.out.println("======欢迎来到莉莉影城======");
            System.out.println("1.登录");
            System.out.println("2.用户注册");
            System.out.println("3.商家入驻");
            System.out.println("请输入操作命令：");
            String command = SCANNER.nextLine();
            switch (command) {
                case "1":
                    // 登录
                    login();
                    break;
                case "2":
                    // 用户注册
                    customerRegister();
                    break;
                case "3":
                    // 商家入驻
                    businessRegister();
                    break;
                default:
                    System.out.println("您输入的指令有误，请重新输入");
            }
        }
    }

    private static void customerRegister() {
        System.out.println("======顾客注册界面======");
        String loginName;
        while (true) {
            System.out.println("请输入您的用户名：");
            loginName = SCANNER.nextLine();
            for (User user : USERS) {
                if (user.getLoginName().equals(loginName) ) {
                    System.out.println("此用户名已被占用，请重新输入");
                    continue;
                }
            }
            break;
        }
        System.out.println("请设置您的密码：");
        String password = SCANNER.nextLine();
        System.out.println("请输入您的姓名：");
        String name = SCANNER.nextLine();
        System.out.println("请输入您的性别：女/男");
        String gender = SCANNER.nextLine();
        System.out.println("请输入您的手机号码：");
        String phoneNumber = SCANNER.nextLine();
        System.out.println("请输入您的充值金额：");
        String recharge = SCANNER.nextLine();
        double money = Double.valueOf(recharge);

        User customer = new Customer(loginName, password, name, gender.charAt(0), phoneNumber, money);
        USERS.add(customer);
        System.out.println("您已成功注册，用户名：" + loginName + "，余额：" + money);
    }

    private static void businessRegister() {
        System.out.println("======商家入驻界面======");
        String loginName;
        while (true) {
            System.out.println("请输入您的用户名：");
            loginName = SCANNER.nextLine();
            for (User user : USERS) {
                if (user.getLoginName().equals(loginName) ) {
                    System.out.println("此用户名已被占用，请重新输入");
                    continue;
                }
            }
            break;
        }
        System.out.println("请设置您的密码：");
        String password = SCANNER.nextLine();
        System.out.println("请输入您的姓名：");
        String name = SCANNER.nextLine();
        System.out.println("请输入您的性别：女/男");
        String gender = SCANNER.nextLine();
        System.out.println("请输入您的手机号码：");
        String phoneNumber = SCANNER.nextLine();
        System.out.println("请输入您的充值金额：");
        String recharge = SCANNER.nextLine();
        double money = Double.valueOf(recharge);
        System.out.println("请输入您的店铺名：");
        String shopName = SCANNER.nextLine();
        System.out.println("请输入您的店铺地址：");
        String address = SCANNER.nextLine();

        User business = new Business(loginName, password, name, gender.charAt(0), phoneNumber, money, shopName, address);
        USERS.add(business);
        INFO.put((Business) business, new ArrayList<Movie>());
        System.out.println("您已成功注册，用户名：" + loginName + "，商家名：" + shopName + "，店铺地址：" + address);
    }

    private static void login() {
        while (true) {
            System.out.println("请输入您的登录名称：");
            String loginName = SCANNER.nextLine();
            System.out.println("请输入您的密码");
            String password = SCANNER.nextLine();

            User user = getUserByLoginName(loginName);
            if (user == null) {
                System.out.println("登录名错误，请重新输入您的信息");
            } else {
                if (user.getPassword().equals(password)) {
                    loginUser = user;
                    LOGGER.info(user.getLoginName() + "登录了电影系统");
                    System.out.println("您已成功登录");
                    if (user instanceof Customer) {
                        showCustomerPage();
                    } else {
                        showBusinessPage();
                    }
                    break;
                } else {
                    System.out.println("密码错误，请重新输入您的信息");
                }
            }
        }
    }

    // 顾客主页
    private static void showCustomerPage() {
        System.out.println("======欢迎来到客户主页======");

        while (true) {
            System.out.println("1.展示全部影片");
            System.out.println("2.根据电影名查询电影信息");
            System.out.println("3.评分");
            System.out.println("4.购票");
            System.out.println("5.退出系统");
            System.out.println("请输入操作指令：");
            String command = SCANNER.nextLine();
            switch (command) {
                case "1":
                    showAllMovies();
                    break;
                case "2":
                    searchMovieByName();
                    break;
                case "3":
                    rateMovie();
                    break;
                case "4":
                    buyTicket();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("您输入的指令有误，请重新输入");
                    break;
            }
        }
    }

    private static void showAllMovies() {
        System.out.println("======查看所有排片======");
        INFO.forEach((business, movies) -> {
            System.out.println(business.getShopName() + "\t\t电话：" + business.getPhoneNumber() + "\t\t地址：" + business.getAddress());
            System.out.println("\t片名\t\t\t主演\t\t时长\t\t评分\t\t票价\t\t余票\t\t放映时间");
            for (Movie movie : movies) {
                System.out.println("\t" + movie.getName() + "\t\t" + movie.getStar() + "\t\t" + movie.getDuration() + "\t\t"
                        + movie.getRate() + "\t\t" + movie.getPrice() + "\t\t" + movie.getTicketNumber() + "\t\t"
                        + sdf.format(movie.getStartTime()));
            }
        });
    }

    // public static final Map<Business, List<Movie>> INFO = new HashMap<>();
    private static Movie searchMovieByName() {
        System.out.println("请输入您想要查询的电影：");
        String name = SCANNER.nextLine();
        Set<Business> businesses = INFO.keySet();
        for (Business business : businesses) {
            List<Movie> movies = INFO.get(business);
            for (Movie movie : movies) {
                if (movie.getName().contains(name)) {
                    System.out.println("上映影院：" + business.getShopName() + " 电影名：" + movie.getName() +
                            " 票价：" + movie.getPrice() + " 上映时间：" + movie.getStartTime());
                    return movie;
                }
            }
        }
        System.out.println("查询完毕");
        return null;
    }

    private static Movie returnMovieByName(String name) {
        Set<Business> businesses = INFO.keySet();
        for (Business business : businesses) {
            List<Movie> movies = INFO.get(business);
            for (Movie movie : movies) {
                if (movie.getName().contains(name)) {
                    return movie;
                }
            }
        }
        return null;
    }

    private static void rateMovie() {
        // 1、查询当前登录成功的用户历史购买记录，看哪些电影可以评分
        Customer c = (Customer) loginUser;
        // 买过的电影
        Map<String, Boolean> movieRated = c.getMovieRated();
        if (movieRated.size() == 0) {
            System.out.println("您没有购买过该电影，请购买后评价！");
        }
        movieRated.forEach((name, flag) -> {
            if (flag) {
                System.out.println("您已评价过该电影，请勿重复评价");
            } else {
                System.out.println("请您对" + name + "电影打分（0-10）：");
                double score = Double.valueOf(SCANNER.nextLine());
                // 这里每个店铺的电影是单独评分的。
                // 更好的方法是：在系统中创建一个静态的map来存储电影的评分。这样所有店铺的该电影的评分都是统一的
                // 获取该电影的历史评分数据
                Movie movie = returnMovieByName(name);
                if (movie.getScores() == null) {
                    movie.setScores(new ArrayList<>());
                }
                List<Double> scores = movie.getScores();
                // 更新数据
                scores.add(score);
                movie.setScores(scores);
                Double scoreSum = 0.0;
                List<Double> updatedScores = movie.getScores();
                for (Double updatedScore : updatedScores) {
                    scoreSum += updatedScore;
                }
                movie.setScore(BigDecimal.valueOf(scoreSum).divide(BigDecimal.valueOf(movie.getScores().size()), 2 , RoundingMode.UP).doubleValue());
                movieRated.put(name, true);
                System.out.println("您已成功打分，评分为：" + movie.getScore());
            }
        });


    }

    private static void buyTicket() {
        showAllMovies();
        System.out.println("======用户购票======");
        while (true) {
            System.out.println("请输入您想要购票的门店：");
            String shopName = SCANNER.nextLine();
            Business business = getBusinessByShopName(shopName);
            if (business == null) {
                System.out.println("店名不存在，请重新输入");
            } else {
                List<Movie> movies = INFO.get(business);
                if (movies.size() > 0) {
                    while (true) {
                        System.out.println("请输入想要购买的电影名称");
                        String movieName = SCANNER.nextLine();
                        Movie movie = getMovieByName2(movieName, movies);
                        if (movie == null) {
                            System.out.println("不存在该电影，请重新输入");
                        } else {
                            while (true) {
                                System.out.println("请输入您要购买的票数：");
                                String ticketNumber = SCANNER.nextLine();
                                int ticketNum = Integer.valueOf(ticketNumber);
                                if (ticketNum > movie.getTicketNumber()) {
                                    System.out.println("余票不足，请您选择接下来的操作");
                                    System.out.println("1.重新输入购买票数");
                                    System.out.println("2.去别的店逛逛");
                                    System.out.println("3.退出购票");
                                    String command = SCANNER.nextLine();
                                    switch (command) {
                                        case "1":
                                            break;
                                        case "2":
                                            buyTicket();
                                        default:
                                            System.out.println("您已退出购票操作");
                                            return;
                                    }
                                } else {
                                    // cost = price * ticketNum
                                    double cost = BigDecimal.valueOf(movie.getPrice()).multiply(BigDecimal.valueOf(ticketNum)).doubleValue();
                                    if (cost <= loginUser.getMoney()) {
                                        loginUser.setMoney(loginUser.getMoney() - cost);
                                        movie.setTicketNumber(movie.getTicketNumber() - ticketNum);
                                        System.out.println("您已成功购票");

                                        // 记录购买电影的信息，此时处于可以评价但还未评价的状态
                                        Customer c = (Customer) loginUser;
                                        c.getMovieRated().put(movie.getName(), false);
                                        return;
                                    } else {
                                        System.out.println("您的余额不足");
                                    }
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("该电影院打烊了~");
                    System.out.println("是否继续购票？ y/n");
                    String command = SCANNER.nextLine();
                    switch (command) {
                        case "y":
                            break;
                        default:
                            System.out.println("您已退出购票操作");
                            return;
                    }
                }
            }
        }
    }

    // 查询当前商家的电影（商家登录）
    private static Movie getMovieByName(String name) {
        Business business = (Business) loginUser;
        List<Movie> movies = INFO.get(business);

        for (Movie movie : movies) {
            if (movie.getName().contains(name)) {
                return movie;
            }
        }
        return null;
    }

    // 查询当前商家的电影
    private static Movie getMovieByName2(String name, List<Movie> movies) {
        for (Movie movie : movies) {
            if (movie.getName().contains(name)) {
                return movie;
            }
        }
        return null;
    }

    private static Business getBusinessByShopName(String shopName) {
        Set<Business> businesses = INFO.keySet();
        for (Business business : businesses) {
            if (business.getShopName().equals(shopName)) {
                return business;
            }
        }
        return null;
    }

    // 商家主页
    private static void showBusinessPage() {
        System.out.println("======商家系统为您服务======");
        System.out.println(loginUser.getLoginName() + (loginUser.getGender() == '女' ? "女士" : "先生") + "，欢迎光临");

        while (true) {
            System.out.println("1.展示详情");
            System.out.println("2.上架电影");
            System.out.println("3.下架电影");
            System.out.println("4.修改电影");
            System.out.println("5.退出系统");
            System.out.println("请输入操作指令：");
            String command = SCANNER.nextLine();
            switch (command) {
                case "1":
                    showBusinessInfo();
                    break;
                case "2":
                    addMovie();
                    break;
                case "3":
                    deleteMovie();
                    break;
                case "4":
                    updateMovie();
                    break;
                case "5":
                    System.out.println("您已成功退出");
                    return;
                default:
                    System.out.println("您输入的指令有误，请重新输入");
                    break;
            }
        }
    }

    private static void updateMovie() {
        System.out.println("======修改电影信息界面======");
        Business business = (Business) loginUser;
        List<Movie> movies = INFO.get(business);

        if (movies.size() == 0) {
            System.out.println("您目前没有电影上映，因此无法修改电影");
            return;
        }
        while (true) {
            System.out.println("请输入需要修改信息的电影名称：");
            String name = SCANNER.nextLine();
            Movie movie = getMovieByName(name);
            if (movie != null) {
                System.out.println("请您输入新片名：");
                String movieName = SCANNER.nextLine();
                System.out.println("请您输入新主演：");
                String star = SCANNER.nextLine();
                System.out.println("请您输入新时长：");
                String duration = SCANNER.nextLine();
                System.out.println("请您输入新票价：");
                String price = SCANNER.nextLine();
                System.out.println("请您输入新票数：");
                String ticketNumber = SCANNER.nextLine();

                while (true) {
                    try {
                        System.out.println("请您输入新的放映时间：");
                        String startTime = SCANNER.nextLine();
                        movie.setName(movieName);
                        movie.setStar(star);
                        movie.setDuration(Double.valueOf(duration));
                        movie.setPrice(Double.valueOf(price));
                        movie.setTicketNumber(Integer.valueOf(ticketNumber));
                        movie.setStartTime(sdf.parse(startTime));
                        System.out.println("您已成功修改影片信息：" + movie.getName());
                        System.out.println("电影信息已更新");
                        showBusinessInfo();
                        return;
                    } catch (ParseException e) {
                        e.printStackTrace();
                        LOGGER.error("startTime解析出了问题");
                    }
                }
            } else {
                System.out.println("您的店铺未上架该影片，是否继续修改操作？ y/n");
                String command = SCANNER.nextLine();
                switch (command) {
                    case "y":
                        break;
                    default:
                        System.out.println("您已退出修改操作");
                        return;
                }
            }
        }
    }

    private static void deleteMovie() {
        System.out.println("======电影下架界面======");
        Business business = (Business) loginUser;
        List<Movie> movies = INFO.get(business);

        if (movies.size() == 0) {
            System.out.println("您目前没有电影上映，因此无法下架电影");
            return;
        }
        while (true) {
            System.out.println("请输入需要下架的电影名称：");
            String name = SCANNER.nextLine();
            Movie movie = getMovieByName(name);
            if (movie != null) {
                movies.remove(movie);
                System.out.println("电影已成功下架");
                showBusinessInfo();
                return;
            } else {
                System.out.println("您的店铺未上架该影片，是否继续下架操作？ y/n");
                String command = SCANNER.nextLine();
                switch (command) {
                    case "y":
                        break;
                    default:
                        System.out.println("您已退出下架操作");
                        return;
                }
            }
        }
    }

    private static void addMovie() {
        System.out.println("======电影上架界面======");
        Business business = (Business) loginUser;
        List<Movie> movies = INFO.get(business);

        System.out.println("请您输入新片名：");
        String name = SCANNER.nextLine();
        System.out.println("请您输入主演：");
        String star = SCANNER.nextLine();
        System.out.println("请您输入时长：");
        String duration = SCANNER.nextLine();
        System.out.println("请您输入票价：");
        String price = SCANNER.nextLine();
        System.out.println("请您输入票数：");
        String ticketNumber = SCANNER.nextLine();

        while (true) {
            try {
                System.out.println("请您输入放映时间：");
                String startTime = SCANNER.nextLine();
                Movie movie = new Movie(name, star, Double.valueOf(duration), sdf.parse(startTime), Double.valueOf(price), Integer.valueOf(ticketNumber));
                movies.add(movie);
                System.out.println("您已成功上架影片：" + movie.getName());
                return;
            } catch (ParseException e) {
                e.printStackTrace();
                LOGGER.error("startTime解析出了问题");
            }
        }
    }

    private static void showBusinessInfo() {
        System.out.println("======商家详情界面======");
        LOGGER.info(loginUser.getLoginName() + "商家正在看自己的详情页");
        Business business = (Business) loginUser;
        System.out.println(business.getShopName() + "\t\t电话：" + business.getPhoneNumber() + "\t\t地址：" + business.getAddress());
        List<Movie> movies = INFO.get(loginUser);
        System.out.println("片名\t\t\t主演\t\t时长\t\t评分\t\t票价\t\t余票\t\t放映时间");
        if (movies.size() == 0) {
            System.out.println("您的店铺当前没有电影");
        }
        for (Movie movie : movies) {
            System.out.println(movie.getName() + "\t\t" + movie.getStar() + "\t\t" + movie.getDuration() + "\t\t"
                + movie.getRate() + "\t\t" + movie.getPrice() + "\t\t" + movie.getTicketNumber() + "\t\t"
                + sdf.format(movie.getStartTime()));
        }
    }

    // 通过用户名返回用户对象
    private static User getUserByLoginName(String loginName) {
        for (User user : USERS) {
            if (user.getLoginName().equals(loginName)) {
                return user;
            }
        }
        return null;
    }

    // 测试数据
    static {
        Customer c1 = new Customer();
        c1.setLoginName("zyf888");
        c1.setPassword("123456");
        c1.setName("黑马刘德华");
        c1.setGender('男');
        c1.setMoney(10000);
        c1.setPhoneNumber("110110");
        USERS.add(c1);

        Customer c2 = new Customer();
        c2.setLoginName("gzl888");
        c2.setPassword("123456");
        c2.setName("黑马关之琳");
        c2.setGender('女');
        c2.setMoney(2000);
        c2.setPhoneNumber("111111");
        USERS.add(c2);

        Business b1 = new Business();
        b1.setLoginName("baozugong888");
        b1.setPassword("123456");
        b1.setName("黑马包租公");
        b1.setMoney(0);
        b1.setGender('男');
        b1.setPhoneNumber("110110");
        b1.setAddress("火星6号2B二层");
        b1.setShopName("甜甜圈国际影城");
        USERS.add(b1);
        // 注意，商家一定需要加入到店铺排片信息中去
        List<Movie> movies1 = new ArrayList<>(); // []
        INFO.put(b1, movies1); // b1 = []

        Business b2 = new Business();
        b2.setLoginName("baozupo888");
        b2.setPassword("123456");
        b2.setName("黑马包租婆");
        b2.setMoney(0);
        b2.setGender('女');
        b2.setPhoneNumber("110110");
        b2.setAddress("火星8号8B八层");
        b2.setShopName("巧克力国际影城");
        USERS.add(b2);
        // 注意，商家一定需要加入到店铺排片信息中去
        List<Movie> movies2 = new ArrayList<>();
        INFO.put(b2, movies2); // b2 = []
    }
}
