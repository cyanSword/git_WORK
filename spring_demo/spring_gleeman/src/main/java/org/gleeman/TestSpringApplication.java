package org.gleeman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;


@SpringBootApplication
@RestController
// extends javafx.application.Application 继承能创建界面的相关特性，lunch()启动主界面
public class TestSpringApplication extends javafx.application.Application{
    @Autowired
    private JdbcProperties jdbcProperties;

    @Autowired
    private JdbcProperties1 jdbcProperties1;

    @RequestMapping("testProfile")
    public JdbcProperties testProfile() {
        return jdbcProperties;
    }

    @RequestMapping("testProfile1")
    public JdbcProperties1 testProfile1() {
        return jdbcProperties1;
    }

    public static void main(String[] args) {

        SpringApplication.run(TestSpringApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceLoader resourceLoader=new DefaultResourceLoader();
        URL url= resourceLoader.getResource("sample.fxml").getURL();
        //Parent root = FXMLLoader.load(getClass().getResource("../../../resources/sample.fxml"));
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Gleeman");
        primaryStage.setScene(new Scene(root, 680, 450));
        primaryStage.show();
    }
}
