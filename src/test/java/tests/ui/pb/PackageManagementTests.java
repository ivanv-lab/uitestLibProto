package tests.ui.pb;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testlib.base.NavbarWorker;
import testlib.base.TableWorker;
import testlib.base.pb.PBBaseTest;
import testlib.pages.pbui.PackageManagementPage;

import java.util.stream.Stream;

public class PackageManagementTests extends PBBaseTest {

    private PackageManagementPage packageManagementPage=new PackageManagementPage();
    private NavbarWorker navbarWorker=new NavbarWorker();
    private TableWorker tableWorker=new TableWorker();

    static Stream<Arguments> packageList(){
        return Stream.of(
                Arguments.of("pack1","111","Бесплатный","Активный","qweqwe",new String[]{"2025","Июнь","4"},
                        new String[]{"2026","Июнь","6"},"tariff","12",true),
                Arguments.of("pack2","222","Платный","Активный","qweqwe",new String[]{"2025","Июнь","4"},
                        new String[]{"2026","Июнь","6"},"tariff","12",true),
                Arguments.of("pack3","333","Бесплатный","Неактивный","qweqwe",new String[]{"2025","Июнь","5"},
                        new String[]{"2029","Август","2"},"tariff","12",true),
                Arguments.of("pack4","444","Платный","Неактивный","qweqwe",new String[]{"2025","Июнь","6"},
                        new String[]{"2028","Июнь","6"},"tariff","12",true)
        );
    }

    @ParameterizedTest
    @Tag("pb-ui")
    @Description("Создание пакета")
    @MethodSource("packageList")
    void packCreateTests(String name, String code, String subType, String status, String desc,String[] startDate,
                         String[] endDate,String tariff,String period,boolean nds){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.createPack();

        packageManagementPage.setNameInput(name);
        packageManagementPage.setCodeInput(code);
        packageManagementPage.setSubTypeInput(subType);
        packageManagementPage.setStatusInput(status);
        packageManagementPage.setDescriptionInput(desc);
        packageManagementPage.setStartDate(startDate[0],startDate[1],startDate[2]);
        packageManagementPage.setEndDate(endDate[0],endDate[1],endDate[2]);
        packageManagementPage.setTariffInput(tariff);
        packageManagementPage.setPeriodInput(period);
        if(nds)
            packageManagementPage.ndsOn();
        else packageManagementPage.ndsOff();

        tableWorker.tableRowExists(name);
    }
}
