package tests.ui.pb;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import testlib.base.NavbarWorker;
import testlib.base.TableWorker;
import testlib.base.pb.PBBaseTest;
import testlib.pages.pbui.PackageEventsManagementPage;
import testlib.utils.TagOrderer;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(TagOrderer.class)
@DisplayName("Тестирование страницы Управление событиями пакета PBUI")
@Tag("pb-ui")
public class PackageEventsManagementTests extends PBBaseTest {

    private TableWorker tableWorker=new TableWorker();
    private NavbarWorker navbarWorker=new NavbarWorker();
    PackageEventsManagementPage packageEventsManagementPage=new PackageEventsManagementPage();

    static Stream<Arguments> packageEventList(){
        return Stream.of(
                Arguments.of("pack1","event1LowTrue","Push","tempPush",true,true,true,true,true,"SMS","tempSMS",true,true,true),
                Arguments.of("pack1","event3HighTrue","SMS","tempSMS",false,true,false,true,false,"","",false,false,false,true),
                Arguments.of("pack2","event1LowTrue","Push","tempPush",true,false,true,false,true,"Viber","tempViber",false,true,false),
                Arguments.of("pack3","event4RealtimeFalse","Email","tempEmail",false,false,false,false,false,"","",false,false,true,false),
                Arguments.of("pack4","event1LowTrue","Viber","tempViber",true,true,true,true,true,"Push","tempPush",true,false,false),
                Arguments.of("packNoStartDate","event4RealtimeFalse","WhatsApp","tempWA",false,true,false,true,false,"","",false,false,false,true),
                Arguments.of("packNoNDS","eventNoPriority","Mail Notify","tempMN",true,false,true,false,true,"Email","tempEmail",true,true,false),
                Arguments.of("packNoPeriod","event2NormalFalse","SMS","tempSMS2",false,false,false,false,false,"","",false,false,true,true),
                Arguments.of("packNoTariff","event3HighTrue","Viber","tempViber2",true,true,true,true,true,"WhatsApp","tempWA",true,false,false),
                Arguments.of("packNoEndDate","eventNoPriority","WhatsApp","tempWA2",false,true,false,true,false,"","",false,false,false,true)
        );
    }

    @ParameterizedTest
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    @Description("Создание пакета")
    @MethodSource("packageEventList")
    void packEventCreate(String pack,String event,String channel,String template,boolean transliterate,boolean important,boolean emailSending,
                         boolean msisdnSending, boolean imsiOn,String imsiChannel,String imsiTemplate,boolean imsiTransliterate,
                         boolean clientSending,boolean trustedSending){

        navbarWorker.sectionClick("Управление пакетами");

        packageEventsManagementPage.openFilters();

        packageEventsManagementPage.clickPackage(pack);

        packageEventsManagementPage.openFilters();

        assertTrue(packageEventsManagementPage.deleteIfExistsInTable(event));

        packageEventsManagementPage.createEvent();

        packageEventsManagementPage.setEventInput(event);
        packageEventsManagementPage.setChannelInput(channel);
        packageEventsManagementPage.setTemplateInput(template);
        if(transliterate)
            packageEventsManagementPage.setTransliterateInputOn();
        if(important)
            packageEventsManagementPage.setImportantInputOn();
        if(emailSending)
            packageEventsManagementPage.setSendingEmailInputOn();
        if(msisdnSending)
            packageEventsManagementPage.setSendingMSISDNInputOn();
        if(imsiOn) {
            packageEventsManagementPage.setIMSIInputOn();
            packageEventsManagementPage.setIMSIChannelInput(imsiChannel);
            packageEventsManagementPage.setIMSITemplateInput(imsiTemplate);
            if(imsiTransliterate)
                packageEventsManagementPage.setIMSITransliterateInputOn();
        }
        if(clientSending)
            packageEventsManagementPage.setClientSendingInputOn();
        if(trustedSending)
            packageEventsManagementPage.setTrustedPersonSendingInputOn();

        packageEventsManagementPage.clickSaveButton();

        tableWorker.tableRowExists(event);
    }

    ///Отправка

}
