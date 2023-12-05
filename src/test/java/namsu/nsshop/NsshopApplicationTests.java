package namsu.nsshop;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.mockito.ArgumentMatchers.any;


class NsshopApplicationTests {

    NsshopApplication application = new NsshopApplication();

    @Test
    void contextLoads() {
        MockedStatic<SpringApplication> ms = Mockito.mockStatic(SpringApplication.class);
        ms.when(() -> SpringApplication.run((Class<?>) any(), any()))
                .thenReturn(null);
        NsshopApplication.main(null);
        Mockito.verify(SpringApplication.class);
    }

}
