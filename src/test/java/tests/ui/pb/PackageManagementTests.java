package tests.ui.pb;

import org.junit.jupiter.params.provider.Arguments;
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
                Arguments.of("pack1","111","Бесплатный","Активный","qweqwe","")
        );
    }
}
