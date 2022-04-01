package ru.gb.lessons.lesson_8;

//import static com.codeborne.selenide.Selenide.open;

//public class BaseTest {
//    protected WebDriver webDriver;
 //   protected EventFiringWebDriver webDriver;


 //   @BeforeEach
 //   void setUp() {
 //       open("https://pop-music.ru/");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
//        webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
 //       webDriver = new EventFiringWebDriver(WebDriverManager.chromedriver().capabilities(chromeOptions).create());
 //      webDriver.register(new AllureListener());
 //       webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
 //       webDriver.get("https://pop-music.ru/");
//        webDriver.manage().window().setSize(new Dimension(2000, 1500));
 //   }

 //   @AfterEach
 //  void tearDown() {
 //       step("Логи браузера", () -> {
//            webDriver.manage().logs().get(LogType.BROWSER)
//                    .forEach(log -> addAttachment("logs", log.getMessage()));
//        });
 //       webDriver.quit();


 //   }
//}