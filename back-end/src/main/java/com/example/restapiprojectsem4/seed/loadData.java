package com.example.restapiprojectsem4.seed;

import com.example.restapiprojectsem4.entity.*;
import com.example.restapiprojectsem4.repository.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class loadData implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    Faker faker = new Faker();

    @Override
    public void run(String... args) throws Exception {
        loadAccount();
        loadCategory();
        loadProduct();
        loadOrder();
    }

    private void loadAccount(){
        if(accountRepository.count() == 0){
            List<Account> list = new ArrayList<>();
            Account account = new Account();
            account.setId(1);
            account.setUsername("phong123");
            account.setPassword(passwordEncoder.encode("123"));
            account.setPhone("0123456789");
            account.setRole(2);
            account.setAddress(faker.address().fullAddress());
            account.setStatus("1");
            account.setName("phong");
            list.add(account);

            Account account1 = new Account();
            account1.setId(2);
            account1.setUsername("nhat123");
            account1.setPassword(passwordEncoder.encode("123"));
            account1.setPhone("01646316");
            account1.setRole(1);
            account.setAddress(faker.address().fullAddress());
            account1.setStatus("1");
            account1.setName("nh???t");
            list.add(account1);

            Account account2 = new Account();
            account2.setId(3);
            account2.setUsername("an123");
            account2.setPassword(passwordEncoder.encode("123"));
            account2.setPhone("01564613");
            account2.setRole(1);
            account.setAddress(faker.address().fullAddress());
            account2.setStatus("1");
            account2.setName("An");
            list.add(account2);

            Account account3 = new Account();
            account3.setId(4);
            account3.setUsername("duc123");
            account3.setPassword(passwordEncoder.encode("123"));
            account3.setPhone("01613646");
            account.setAddress(faker.address().fullAddress());
            account3.setRole(1);
            account3.setStatus("1");
            account3.setName("?????c");
            list.add(account3);

            Account account4 = new Account();
            account4.setId(5);
            account4.setUsername("hieu123");
            account4.setPassword(passwordEncoder.encode("123"));
            account4.setPhone("0361994666");
            account.setAddress(faker.address().fullAddress());
            account4.setRole(1);
            account4.setStatus("1");
            account4.setName("Hi???u");
            list.add(account4);

            accountRepository.saveAll(list);
        }
    }

    private void loadProduct(){
        if(productRepository.count() == 0){
            List<Product> list = new ArrayList<>();
            Product product = new Product();
            product.setId(1);
            product.setName("L???ng ????n Nam M???");
            product.setPrice(10000);
            product.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271931/Album%20Fruit/qua-Golden-Berry_ueonmn.jpg");
            product.setQuantity(100);
            product.setDescription("????y l?? qu??? l???ng ????n nam m???");
            product.setDetail("Nam M???");
            product.setStatus("ACTIVE");
            product.setCategoryId(1);
            list.add(product);

            Product product1 = new Product();
            product1.setId(2);
            product1.setName("Qu??? V?? S???a V??? V??ng");
            product1.setPrice(15000);
            product1.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271932/Album%20Fruit/vu-sua-hoang-kim_fmzx1c.jpg");
            product1.setQuantity(100);
            product1.setDescription("????y l?? qu??? v?? s???a v??? v??ng");
            product1.setDetail("????i Loan");
            product1.setStatus("ACTIVE");
            product1.setCategoryId(2);
            list.add(product1);

            Product product2= new Product();
            product2.setId(3);
            product2.setName("Qu??? Sung M???");
            product2.setPrice(20000);
            product2.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271931/Album%20Fruit/sung-my_vrjuy2.jpg");
            product2.setQuantity(100);
            product2.setDescription("????y l?? qu??? sung M???");
            product2.setDetail("M???");
            product2.setStatus("ACTIVE");
            product2.setCategoryId(3);
            list.add(product2);

            Product product3 = new Product();
            product3.setId(4);
            product3.setName("Qu??? S??m S???a");
            product3.setPrice(30000);
            product3.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271931/Album%20Fruit/qua-sam-sua_retzdj.jpg");
            product3.setQuantity(100);
            product3.setDescription("????y l?? qu??? S??m S???a");
            product3.setDetail("???? L???t");
            product3.setStatus("ACTIVE");
            product3.setCategoryId(4);
            list.add(product3);

            Product product4 = new Product();
            product4.setId(5);
            product4.setName("Qu??? H???ng Gi??n");
            product4.setPrice(35000);
            product4.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271931/Album%20Fruit/qua-hong-gion_istiy5.jpg");
            product4.setQuantity(100);
            product4.setDescription("????y l?? qu??? h???ng gi??n");
            product4.setDetail("???? L???t");
            product4.setStatus("ACTIVE");
            product4.setCategoryId(1);
            list.add(product4);

            Product product5 = new Product();
            product5.setId(6);
            product5.setName("Qu??? B?? d??i");
            product5.setPrice(36000);
            product5.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271931/Album%20Fruit/qua-bo-034_kk2wj4.jpg");
            product5.setQuantity(100);
            product5.setDescription("????y l?? qu??? b?? d??i");
            product5.setDetail("???? L???t");
            product5.setStatus("ACTIVE");
            product5.setCategoryId(2);
            list.add(product5);

            Product product6 = new Product();
            product6.setId(7);
            product6.setName("Qu??? b?? tr??n");
            product6.setPrice(36000);
            product6.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271931/Album%20Fruit/qua-bo-1_bpqidp.jpg");
            product6.setQuantity(100);
            product6.setDescription("????y l?? qu??? b?? tr??n");
            product6.setDetail("???? L???t");
            product6.setStatus("ACTIVE");
            product6.setCategoryId(3);
            list.add(product6);

            Product product7 = new Product();
            product7.setId(8);
            product7.setName("Ph??c B???n T??? ?????");
            product7.setPrice(36000);
            product7.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271931/Album%20Fruit/phuc-bon-tu-do-1_eeicmy.jpg");
            product7.setQuantity(100);
            product7.setDescription("????y l?? qu??? ph??c b???n t??? ?????");
            product7.setDetail("Israel");
            product7.setStatus("ACTIVE");
            product7.setCategoryId(4);
            list.add(product7);

            Product product8 = new Product();
            product8.setId(9);
            product8.setName("Ph??c B???n T??? ??en");
            product8.setPrice(36000);
            product8.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271930/Album%20Fruit/phuc-bon-tu-den-4_jffzam.jpg");
            product8.setQuantity(100);
            product8.setDescription("????y l?? qu??? ph??c b???n t??? ??en");
            product8.setDetail("Israel");
            product8.setStatus("ACTIVE");
            product8.setCategoryId(1);
            list.add(product8);

            Product product9 = new Product();
            product9.setId(10);
            product9.setName("D??a Pepino t??m");
            product9.setPrice(36000);
            product9.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271930/Album%20Fruit/dua-pepino-tim_voixfg.jpg");
            product9.setQuantity(100);
            product9.setDescription("????y l?? qu??? d??a pepino t??m");
            product9.setDetail("???? L???t");
            product9.setStatus("ACTIVE");
            product9.setCategoryId(2);
            list.add(product9);

            Product product10 = new Product();
            product10.setId(11);
            product10.setName("D??u t??y Nh???t B???n");
            product10.setPrice(36000);
            product10.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271930/Album%20Fruit/dau-tay-nhat-ban-tphcm_iygrcd.jpg\n");
            product10.setQuantity(100);
            product10.setDescription("????y l?? qu??? d??u t??y Nh???t B???n");
            product10.setDetail("Nh???t B???n");
            product10.setStatus("ACTIVE");
            product10.setCategoryId(2);
            list.add(product10);

            Product product11 = new Product();
            product11.setId(12);
            product11.setName("D??u t??y New Zealand");
            product11.setPrice(36000);
            product11.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271930/Album%20Fruit/dau-tay-new-zealand-hcm-2_vxfbw8.jpg");
            product11.setQuantity(100);
            product11.setDescription("????y l?? qu??? d??u t??y New Zealand");
            product11.setDetail("New Zealand");
            product11.setStatus("ACTIVE");
            product11.setCategoryId(4);
            list.add(product11);

            Product product12 = new Product();
            product12.setId(13);
            product12.setName("D??u t??y M??? ");
            product12.setPrice(36000);
            product12.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271929/Album%20Fruit/dau-tay-my-dalat_tmgfxk.jpg");
            product12.setQuantity(100);
            product12.setDescription("????y l?? qu??? d??u t??y M??? ");
            product12.setDetail("M??? ");
            product12.setStatus("ACTIVE");
            product12.setCategoryId(1);
            list.add(product12);

            Product product13 = new Product();
            product13.setId(14);
            product13.setName("D??u t??y ???? L???t ");
            product13.setPrice(36000);
            product13.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271929/Album%20Fruit/dau-tay-da-lat_zaaciz.jpg");
            product13.setQuantity(100);
            product13.setDescription("????y l?? qu??? d??u t??y ???? L???t ");
            product13.setDetail("???? L???t ");
            product13.setStatus("ACTIVE");
            product13.setCategoryId(2);
            list.add(product13);

            Product product14 = new Product();
            product14.setId(15);
            product14.setName("D??u t??y B???ch Tuy???t ");
            product14.setPrice(36000);
            product14.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/dau-anh-dao-nhat_qwmsm1.jpg");
            product14.setQuantity(100);
            product14.setDescription("????y l?? qu??? d??u t??y B???ch Tuy???t ");
            product14.setDetail("Nh???t B???n ");
            product14.setStatus("ACTIVE");
            product14.setCategoryId(4);
            list.add(product14);

            Product product15 = new Product();
            product15.setId(16);
            product15.setName("D??u T???m ");
            product15.setPrice(36000);
            product15.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271929/Album%20Fruit/dau-tam-5_a9rykw.jpg    ");
            product15.setQuantity(100);
            product15.setDescription("????y l?? qu??? d??u T???m ");
            product15.setDetail("???? L???t ");
            product15.setStatus("ACTIVE");
            product15.setCategoryId(4);
            list.add(product15);

            Product product16 = new Product();
            product16.setId(17);
            product16.setName("D??u t??y ???? L???t ");
            product16.setPrice(36000);
            product16.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271929/Album%20Fruit/dau-tay-da-lat_zaaciz.jpg");
            product16.setQuantity(100);
            product16.setDescription("????y l?? qu??? d??u t??y ???? L???t ");
            product16.setDetail("???? L???t ");
            product16.setStatus("ACTIVE");
            product16.setCategoryId(3);
            list.add(product16);

            Product product17 = new Product();
            product17.setId(18);
            product17.setName("Chanh Leo V??ng Ng???t Ho??ng Kim");
            product17.setPrice(36000);
            product17.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/chanh-leo-vang-ngot-hoang-kim_rhyucm.jpg");
            product17.setQuantity(100);
            product17.setDescription("????y l?? qu??? Chanh Leo V??ng Ng???t Ho??ng Kim");
            product17.setDetail("???? L???t ");
            product17.setStatus("ACTIVE");
            product17.setCategoryId(2);
            list.add(product17);

            Product product18 = new Product();
            product18.setId(19);
            product18.setName("Chanh leo ngot colombia");
            product18.setPrice(36000);
            product18.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/chanh-leo-ngot-colombia_k2cnoo.jpg");
            product18.setQuantity(100);
            product18.setDescription("????y l?? qu??? Chanh leo ngot colombia");
            product18.setDetail("CoLombia");
            product18.setStatus("ACTIVE");
            product18.setCategoryId(3);
            list.add(product18);

            Product product19 = new Product();
            product19.setId(20);
            product19.setName("Chanh d??y");
            product19.setPrice(36000);
            product19.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/chanh-day_q8lnk2.jpg");
            product19.setQuantity(100);
            product19.setDescription("????y l?? qu??? Chanh d??y");
            product19.setDetail("???? L???t");
            product19.setStatus("ACTIVE");
            product19.setCategoryId(4);
            list.add(product19);

            Product product20 = new Product();
            product20.setId(21);
            product20.setName("C?? Chua Bi V??ng");
            product20.setPrice(36000);
            product20.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/ca-chua-bi-vang-1_qhvyph.jpg");
            product20.setQuantity(100);
            product20.setDescription("????y l?? qu??? C?? Chua Bi V??ng");
            product20.setDetail("???? L???t");
            product20.setStatus("ACTIVE");
            product20.setCategoryId(1);
            list.add(product20);

            Product product21 = new Product();
            product21.setId(22);
            product21.setName("C?? Chua Socola");
            product21.setPrice(36000);
            product21.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/ca-chua-socola_qqyogw.jpg");
            product21.setQuantity(100);
            product21.setDescription("????y l?? qu??? C?? Chua Socola");
            product21.setDetail("???? L???t");
            product21.setStatus("ACTIVE");
            product21.setCategoryId(2);
            list.add(product21);

            Product product22 = new Product();
            product22.setId(23);
            product22.setName("C?? Chua Bi ??en");
            product22.setPrice(36000);
            product22.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271927/Album%20Fruit/ca-chua-bi-den_l9n6mk.jpg");
            product22.setQuantity(100);
            product22.setDescription("????y l?? qu??? C?? Chua Bi ??en");
            product22.setDetail("TP. H??? Ch?? Minh");
            product22.setStatus("ACTIVE");
            product22.setCategoryId(3);
            list.add(product22);

            Product product23 = new Product();
            product23.setId(24);
            product23.setName("???t ng???t sweet pepper");
            product23.setPrice(36000);
            product23.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271930/Album%20Fruit/ot-ngot-dai-Palermo_jf2ygd.jpg");
            product23.setQuantity(100);
            product23.setDescription("????y l?? qu??? ???t ng???t sweet pepper");
            product23.setDetail("???? L???t");
            product23.setStatus("ACTIVE");
            product23.setCategoryId(4);
            list.add(product23);

            Product product24 = new Product();
            product24.setId(25);
            product24.setName("B?? ???? L???t");
            product24.setPrice(36000);
            product24.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271927/Album%20Fruit/bo-sap-034_mp5dr9.jpg");
            product24.setQuantity(100);
            product24.setDescription("????y l?? qu??? B?? ???? L???t");
            product24.setDetail("???? L???t");
            product24.setStatus("ACTIVE");
            product24.setCategoryId(3);
            list.add(product24);

            Product product25 = new Product();
            product25.setId(26);
            product25.setName("M???t H???ng D???o");
            product25.setPrice(36000);
            product25.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273236/Album%20Fruit/mut-hong-deo-300x300_yib6cd.jpg");
            product25.setQuantity(100);
            product25.setDescription("????y l?? M???t H???ng D???o");
            product25.setDetail("???? L???t");
            product25.setStatus("ACTIVE");
            product25.setCategoryId(2);
            list.add(product25);

            Product product26 = new Product();
            product26.setId(27);
            product26.setName("Th???p C???m S???y ???? L???t");
            product26.setPrice(36000);
            product26.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273237/Album%20Fruit/thap-cam-say-da-lat-300x300_o7rudi.jpg");
            product26.setQuantity(100);
            product26.setDescription("????y l?? Th???p C???m S???y ???? L???t");
            product26.setDetail("???? L???t");
            product26.setStatus("ACTIVE");
            product26.setCategoryId(3);
            list.add(product26);

            Product product27 = new Product();
            product27.setId(28);
            product27.setName("M??t S???y Gi??n");
            product27.setPrice(36000);
            product27.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273236/Album%20Fruit/mit-say-gion-300x300_mqcmx7.jpg");
            product27.setQuantity(100);
            product27.setDescription("????y l?? M??t S???y Gi??n");
            product27.setDetail("???? L???t");
            product27.setStatus("ACTIVE");
            product27.setCategoryId(4);
            list.add(product27);

            Product product28 = new Product();
            product28.setId(29);
            product28.setName("D??u mix ph??c b???n t???");
            product28.setPrice(36000);
            product28.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273236/Album%20Fruit/dau-nhat-mix-phuc-bon-tu_zxnuah.jpg");
            product28.setQuantity(100);
            product28.setDescription("????y l?? D??u mix ph??c b???n t???");
            product28.setDetail("???? L???t");
            product28.setStatus("ACTIVE");
            product28.setCategoryId(1);
            list.add(product28);

            Product product29 = new Product();
            product29.setId(30);
            product29.setName("H???ng Treo");
            product29.setPrice(36000);
            product29.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/hong-treo-300x300_rqqsnu.jpg");
            product29.setQuantity(100);
            product29.setDescription("????y l?? H???ng Treo");
            product29.setDetail("???? L???t");
            product29.setStatus("ACTIVE");
            product29.setCategoryId(2);
            list.add(product29);

            Product product30 = new Product();
            product30.setId(31);
            product30.setName("Chanh d??y s???y d???o t??? nhi??n");
            product30.setPrice(36000);
            product30.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/chanh-day-say-deo-tu-nhien-300x300_swzqnh.jpg");
            product30.setQuantity(100);
            product30.setDescription("????y l?? Chanh d??y s???y d???o t??? nhi??n");
            product30.setDetail("???? L???t");
            product30.setStatus("ACTIVE");
            product30.setCategoryId(3);
            list.add(product30);

            Product product31 = new Product();
            product31.setId(32);
            product31.setName("Khoai Lang V??ng S???y Gi??n");
            product31.setPrice(36000);
            product31.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/khoai-lang-vang-say-don-300x300_fdx6rj.jpg");
            product31.setQuantity(100);
            product31.setDescription("????y l?? Khoai Lang V??ng S???y Gi??n");
            product31.setDetail("???? L???t");
            product31.setStatus("ACTIVE");
            product31.setCategoryId(4);
            list.add(product31);

            Product product32 = new Product();
            product32.setId(33);
            product32.setName("Chu???i S???y gi??n ???? L???t");
            product32.setPrice(36000);
            product32.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/chuoi-say-gion-da-lat-300x300_vbydxc.jpg");
            product32.setQuantity(100);
            product32.setDescription("????y l?? Chu???i S???y gi??n ???? L???t");
            product32.setDetail("???? L???t");
            product32.setStatus("ACTIVE");
            product32.setCategoryId(1);
            list.add(product32);

            Product product33 = new Product();
            product33.setId(34);
            product33.setName("Chu???i S???y gi??n ???? L???t");
            product33.setPrice(36000);
            product33.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/chuoi-say-gion-da-lat-300x300_vbydxc.jpg");
            product33.setQuantity(100);
            product33.setDescription("????y l?? Chu???i S???y gi??n ???? L???t");
            product33.setDetail("???? L???t");
            product33.setStatus("ACTIVE");
            product33.setCategoryId(2);
            list.add(product33);

            Product product34 = new Product();
            product34.setId(35);
            product34.setName("Chu???i S???y gi??n ???? L???t");
            product34.setPrice(36000);
            product34.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/chuoi-say-gion-da-lat-300x300_vbydxc.jpg");
            product34.setQuantity(100);
            product34.setDescription("????y l?? Chu???i S???y gi??n ???? L???t");
            product34.setDetail("???? L???t");
            product34.setStatus("ACTIVE");
            product34.setCategoryId(3);
            list.add(product34);

            Product product35 = new Product();
            product35.setId(36);
            product35.setName("Chu???i S???y gi??n ???? L???t");
            product35.setPrice(36000);
            product35.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/chuoi-say-gion-da-lat-300x300_vbydxc.jpg");
            product35.setQuantity(100);
            product35.setDescription("????y l?? Chu???i S???y gi??n ???? L???t");
            product35.setDetail("???? L???t");
            product35.setStatus("ACTIVE");
            product35.setCategoryId(4);
            list.add(product35);

            Product product36 = new Product();
            product36.setId(37);
            product36.setName("Khoai Lang V??ng S???y Gi??n");
            product36.setPrice(36000);
            product36.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/khoai-lang-vang-say-don-300x300_fdx6rj.jpg");
            product36.setQuantity(100);
            product36.setDescription("????y l?? Khoai Lang V??ng S???y Gi??n");
            product36.setDetail("???? L???t");
            product36.setStatus("ACTIVE");
            product36.setCategoryId(1);
            list.add(product36);

            Product product37 = new Product();
            product37.setId(38);
            product37.setName("Khoai Lang V??ng S???y Gi??n");
            product37.setPrice(36000);
            product37.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/khoai-lang-vang-say-don-300x300_fdx6rj.jpg");
            product37.setQuantity(100);
            product37.setDescription("????y l?? Khoai Lang V??ng S???y Gi??n");
            product37.setDetail("???? L???t");
            product37.setStatus("ACTIVE");
            product37.setCategoryId(2);
            list.add(product37);

            Product product38 = new Product();
            product38.setId(39);
            product38.setName("Khoai Lang V??ng S???y Gi??n");
            product38.setPrice(36000);
            product38.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/khoai-lang-vang-say-don-300x300_fdx6rj.jpg");
            product38.setQuantity(100);
            product38.setDescription("????y l?? Khoai Lang V??ng S???y Gi??n");
            product38.setDetail("???? L???t");
            product38.setStatus("ACTIVE");
            product38.setCategoryId(3);
            list.add(product38);

            Product product39 = new Product();
            product39.setId(40);
            product39.setName("Chanh d??y s???y d???o t??? nhi??n");
            product39.setPrice(36000);
            product39.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/chanh-day-say-deo-tu-nhien-300x300_swzqnh.jpg");
            product39.setQuantity(100);
            product39.setDescription("????y l?? Chanh d??y s???y d???o t??? nhi??n");
            product39.setDetail("???? L???t");
            product39.setStatus("ACTIVE");
            product39.setCategoryId(1); //3
            list.add(product39);

            Product product40 = new Product();
            product40.setId(41);
            product40.setName("Chanh d??y s???y d???o t??? nhi??n");
            product40.setPrice(36000);
            product40.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/chanh-day-say-deo-tu-nhien-300x300_swzqnh.jpg");
            product40.setQuantity(100);
            product40.setDescription("????y l?? Chanh d??y s???y d???o t??? nhi??n");
            product40.setDetail("???? L???t");
            product40.setStatus("ACTIVE");
            product40.setCategoryId(2); //3
            list.add(product40);

            Product product41 = new Product();
            product41.setId(42);
            product41.setName("Chanh d??y s???y d???o t??? nhi??n");
            product41.setPrice(36000);
            product41.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/chanh-day-say-deo-tu-nhien-300x300_swzqnh.jpg");
            product41.setQuantity(100);
            product41.setDescription("????y l?? Chanh d??y s???y d???o t??? nhi??n");
            product41.setDetail("???? L???t");
            product41.setStatus("ACTIVE");
            product41.setCategoryId(4); //3
            list.add(product41);

            Product product42 = new Product();
            product42.setId(43);
            product42.setName("H???ng Treo");
            product42.setPrice(36000);
            product42.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273235/Album%20Fruit/hong-treo-300x300_rqqsnu.jpg");
            product42.setQuantity(100);
            product42.setDescription("????y l?? H???ng Treo");
            product42.setDetail("???? L???t");
            product42.setStatus("ACTIVE");
            product42.setCategoryId(1);
            list.add(product42);

            Product product43 = new Product();
            product43.setId(44);
            product43.setName("D??u mix ph??c b???n t???");
            product43.setPrice(36000);
            product43.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273236/Album%20Fruit/dau-nhat-mix-phuc-bon-tu_zxnuah.jpg");
            product43.setQuantity(100);
            product43.setDescription("????y l?? D??u mix ph??c b???n t???");
            product43.setDetail("???? L???t");
            product43.setStatus("ACTIVE");
            product43.setCategoryId(4);
            list.add(product43);

            Product product44 = new Product();
            product44.setId(45);
            product44.setName("M??t S???y Gi??n");
            product44.setPrice(36000);
            product44.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273236/Album%20Fruit/mit-say-gion-300x300_mqcmx7.jpg");
            product44.setQuantity(100);
            product44.setDescription("????y l?? M??t S???y Gi??n");
            product44.setDetail("???? L???t");
            product44.setStatus("ACTIVE");
            product44.setCategoryId(3);
            list.add(product44);

            Product product45 = new Product();
            product45.setId(46);
            product45.setName("M??t S???y Gi??n");
            product45.setPrice(36000);
            product45.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273236/Album%20Fruit/mit-say-gion-300x300_mqcmx7.jpg");
            product45.setQuantity(100);
            product45.setDescription("????y l?? M??t S???y Gi??n");
            product45.setDetail("???? L???t");
            product45.setStatus("ACTIVE");
            product45.setCategoryId(2);
            list.add(product45);

            Product product46 = new Product();
            product46.setId(47);
            product46.setName("M??t S???y Gi??n");
            product46.setPrice(36000);
            product46.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273236/Album%20Fruit/mit-say-gion-300x300_mqcmx7.jpg");
            product46.setQuantity(100);
            product46.setDescription("????y l?? M??t S???y Gi??n");
            product46.setDetail("???? L???t");
            product46.setStatus("ACTIVE");
            product46.setCategoryId(1);
            list.add(product46);

            Product product47 = new Product();
            product47.setId(48);
            product47.setName("Th???p C???m S???y ???? L???t");
            product47.setPrice(36000);
            product47.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273237/Album%20Fruit/thap-cam-say-da-lat-300x300_o7rudi.jpg");
            product47.setQuantity(100);
            product47.setDescription("????y l?? Th???p C???m S???y ???? L???t");
            product47.setDetail("???? L???t");
            product47.setStatus("ACTIVE");
            product47.setCategoryId(1); //3
            list.add(product47);

            Product product48 = new Product();
            product48.setId(49);
            product48.setName("Th???p C???m S???y ???? L???t");
            product48.setPrice(36000);
            product48.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273237/Album%20Fruit/thap-cam-say-da-lat-300x300_o7rudi.jpg");
            product48.setQuantity(100);
            product48.setDescription("????y l?? Th???p C???m S???y ???? L???t");
            product48.setDetail("???? L???t");
            product48.setStatus("ACTIVE");
            product48.setCategoryId(2); //3
            list.add(product48);

            Product product49 = new Product();
            product49.setId(50);
            product49.setName("Th???p C???m S???y ???? L???t");
            product49.setPrice(36000);
            product49.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273237/Album%20Fruit/thap-cam-say-da-lat-300x300_o7rudi.jpg");
            product49.setQuantity(100);
            product49.setDescription("????y l?? Th???p C???m S???y ???? L???t");
            product49.setDetail("???? L???t");
            product49.setStatus("ACTIVE");
            product49.setCategoryId(4); //3
            list.add(product49);

            Product product50 = new Product();
            product50.setId(51);
            product50.setName("M???t H???ng D???o");
            product50.setPrice(36000);
            product50.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273236/Album%20Fruit/mut-hong-deo-300x300_yib6cd.jpg");
            product50.setQuantity(100);
            product50.setDescription("????y l?? M???t H???ng D???o");
            product50.setDetail("???? L???t");
            product50.setStatus("ACTIVE");
            product50.setCategoryId(1);
            list.add(product50);

            Product product51 = new Product();
            product51.setId(52);
            product51.setName("M???t H???ng D???o");
            product51.setPrice(36000);
            product51.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652273236/Album%20Fruit/mut-hong-deo-300x300_yib6cd.jpg");
            product51.setQuantity(100);
            product51.setDescription("????y l?? M???t H???ng D???o");
            product51.setDetail("???? L???t");
            product51.setStatus("ACTIVE");
            product51.setCategoryId(4);
            list.add(product51);

            Product product52 = new Product();
            product52.setId(53);
            product52.setName("C?? Chua Bi ??en");
            product52.setPrice(36000);
            product52.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271927/Album%20Fruit/ca-chua-bi-den_l9n6mk.jpg");
            product52.setQuantity(100);
            product52.setDescription("????y l?? qu??? C?? Chua Bi ??en");
            product52.setDetail("TP. H??? Ch?? Minh");
            product52.setStatus("ACTIVE");
            product52.setCategoryId(1); //3
            list.add(product52);

            Product product53 = new Product();
            product53.setId(54);
            product53.setName("C?? Chua Bi ??en");
            product53.setPrice(36000);
            product53.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271927/Album%20Fruit/ca-chua-bi-den_l9n6mk.jpg");
            product53.setQuantity(100);
            product53.setDescription("????y l?? qu??? C?? Chua Bi ??en");
            product53.setDetail("TP. H??? Ch?? Minh");
            product53.setStatus("ACTIVE");
            product53.setCategoryId(2); //3
            list.add(product53);

            Product product54 = new Product();
            product54.setId(55);
            product54.setName("C?? Chua Bi ??en");
            product54.setPrice(36000);
            product54.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271927/Album%20Fruit/ca-chua-bi-den_l9n6mk.jpg");
            product54.setQuantity(100);
            product54.setDescription("????y l?? qu??? C?? Chua Bi ??en");
            product54.setDetail("TP. H??? Ch?? Minh");
            product54.setStatus("ACTIVE");
            product54.setCategoryId(4); //3
            list.add(product54);

            Product product55 = new Product();
            product55.setId(56);
            product55.setName("C?? Chua Socola");
            product55.setPrice(36000);
            product55.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/ca-chua-socola_qqyogw.jpg");
            product55.setQuantity(100);
            product55.setDescription("????y l?? qu??? C?? Chua Socola");
            product55.setDetail("???? L???t");
            product55.setStatus("ACTIVE");
            product55.setCategoryId(1); //2
            list.add(product55);

            Product product56 = new Product();
            product56.setId(57);
            product56.setName("C?? Chua Socola");
            product56.setPrice(36000);
            product56.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/ca-chua-socola_qqyogw.jpg");
            product56.setQuantity(100);
            product56.setDescription("????y l?? qu??? C?? Chua Socola");
            product56.setDetail("???? L???t");
            product56.setStatus("ACTIVE");
            product56.setCategoryId(3); //2
            list.add(product56);

            Product product57 = new Product();
            product57.setId(58);
            product57.setName("C?? Chua Socola");
            product57.setPrice(36000);
            product57.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/ca-chua-socola_qqyogw.jpg");
            product57.setQuantity(100);
            product57.setDescription("????y l?? qu??? C?? Chua Socola");
            product57.setDetail("???? L???t");
            product57.setStatus("ACTIVE");
            product57.setCategoryId(4); //2
            list.add(product57);

            Product product58 = new Product();
            product58.setId(59);
            product58.setName("C?? Chua Bi V??ng");
            product58.setPrice(36000);
            product58.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/ca-chua-bi-vang-1_qhvyph.jpg");
            product58.setQuantity(100);
            product58.setDescription("????y l?? qu??? C?? Chua Bi V??ng");
            product58.setDetail("???? L???t");
            product58.setStatus("ACTIVE");
            product58.setCategoryId(2);
            list.add(product58);

            Product product59 = new Product();
            product59.setId(60);
            product59.setName("C?? Chua Bi V??ng");
            product59.setPrice(36000);
            product59.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/ca-chua-bi-vang-1_qhvyph.jpg");
            product59.setQuantity(100);
            product59.setDescription("????y l?? qu??? C?? Chua Bi V??ng");
            product59.setDetail("???? L???t");
            product59.setStatus("ACTIVE");
            product59.setCategoryId(3);
            list.add(product59);

            Product product60 = new Product();
            product60.setId(61);
            product60.setName("C?? Chua Bi V??ng");
            product60.setPrice(36000);
            product60.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/ca-chua-bi-vang-1_qhvyph.jpg");
            product60.setQuantity(100);
            product60.setDescription("????y l?? qu??? C?? Chua Bi V??ng");
            product60.setDetail("???? L???t");
            product60.setStatus("ACTIVE");
            product60.setCategoryId(4);
            list.add(product60);

            Product product61 = new Product();
            product61.setId(62);
            product61.setName("Ph??c B???n T??? ?????");
            product61.setPrice(36000);
            product61.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271931/Album%20Fruit/phuc-bon-tu-do-1_eeicmy.jpg");
            product61.setQuantity(100);
            product61.setDescription("????y l?? qu??? ph??c b???n t??? ?????");
            product61.setDetail("Israel");
            product61.setStatus("ACTIVE");
            product61.setCategoryId(1);
            list.add(product61);

            Product product62 = new Product();
            product62.setId(63);
            product62.setName("Ph??c B???n T??? ??en");
            product62.setPrice(36000);
            product62.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271930/Album%20Fruit/phuc-bon-tu-den-4_jffzam.jpg");
            product62.setQuantity(100);
            product62.setDescription("????y l?? qu??? ph??c b???n t??? ??en");
            product62.setDetail("Israel");
            product62.setStatus("ACTIVE");
            product62.setCategoryId(4);
            list.add(product62);

            Product product63 = new Product();
            product63.setId(64);
            product63.setName("B?? ???? L???t");
            product63.setPrice(36000);
            product63.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271927/Album%20Fruit/bo-sap-034_mp5dr9.jpg");
            product63.setQuantity(100);
            product63.setDescription("????y l?? qu??? B?? ???? L???t");
            product63.setDetail("???? L???t");
            product63.setStatus("ACTIVE");
            product63.setCategoryId(4);
            list.add(product63);

            Product product64 = new Product();
            product64.setId(65);
            product64.setName("Chanh Leo V??ng Ng???t Ho??ng Kim");
            product64.setPrice(36000);
            product64.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/chanh-leo-vang-ngot-hoang-kim_rhyucm.jpg");
            product64.setQuantity(100);
            product64.setDescription("????y l?? qu??? Chanh Leo V??ng Ng???t Ho??ng Kim");
            product64.setDetail("???? L???t ");
            product64.setStatus("ACTIVE");
            product64.setCategoryId(1);
            list.add(product64);

            Product product65 = new Product();
            product65.setId(66);
            product65.setName("Chanh leo ngot colombia");
            product65.setPrice(36000);
            product65.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/chanh-leo-ngot-colombia_k2cnoo.jpg");
            product65.setQuantity(100);
            product65.setDescription("????y l?? qu??? Chanh leo ngot colombia");
            product65.setDetail("CoLombia");
            product65.setStatus("ACTIVE");
            product65.setCategoryId(4);
            list.add(product65);

            Product product66 = new Product();
            product66.setId(67);
            product66.setName("Chanh d??y");
            product66.setPrice(36000);
            product66.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/chanh-day_q8lnk2.jpg");
            product66.setQuantity(100);
            product66.setDescription("????y l?? qu??? Chanh d??y");
            product66.setDetail("???? L???t");
            product66.setStatus("ACTIVE");
            product66.setCategoryId(3);
            list.add(product66);

            Product product67 = new Product();
            product67.setId(68);
            product67.setName("D??u t??y Nh???t B???n");
            product67.setPrice(36000);
            product67.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271930/Album%20Fruit/dau-tay-nhat-ban-tphcm_iygrcd.jpg\n");
            product67.setQuantity(100);
            product67.setDescription("????y l?? qu??? d??u t??y Nh???t B???n");
            product67.setDetail("Nh???t B???n");
            product67.setStatus("ACTIVE");
            product67.setCategoryId(1); //2
            list.add(product67);

            Product product68 = new Product();
            product68.setId(69);
            product68.setName("D??u t??y Nh???t B???n");
            product68.setPrice(36000);
            product68.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271930/Album%20Fruit/dau-tay-nhat-ban-tphcm_iygrcd.jpg\n");
            product68.setQuantity(100);
            product68.setDescription("????y l?? qu??? d??u t??y Nh???t B???n");
            product68.setDetail("Nh???t B???n");
            product68.setStatus("ACTIVE");
            product68.setCategoryId(4); //2
            list.add(product68);

            Product product69 = new Product();
            product69.setId(70);
            product69.setName("D??u t??y New Zealand");
            product69.setPrice(36000);
            product69.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271930/Album%20Fruit/dau-tay-new-zealand-hcm-2_vxfbw8.jpg");
            product69.setQuantity(100);
            product69.setDescription("????y l?? qu??? d??u t??y New Zealand");
            product69.setDetail("New Zealand");
            product69.setStatus("ACTIVE");
            product69.setCategoryId(1); //4
            list.add(product69);

            Product product70 = new Product();
            product70.setId(71);
            product70.setName("D??u t??y New Zealand");
            product70.setPrice(36000);
            product70.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271930/Album%20Fruit/dau-tay-new-zealand-hcm-2_vxfbw8.jpg");
            product70.setQuantity(100);
            product70.setDescription("????y l?? qu??? d??u t??y New Zealand");
            product70.setDetail("New Zealand");
            product70.setStatus("ACTIVE");
            product70.setCategoryId(2); //4
            list.add(product70);

            Product product71 = new Product();
            product71.setId(72);
            product71.setName("D??u t??y M??? ");
            product71.setPrice(36000);
            product71.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271929/Album%20Fruit/dau-tay-my-dalat_tmgfxk.jpg");
            product71.setQuantity(100);
            product71.setDescription("????y l?? qu??? d??u t??y M??? ");
            product71.setDetail("M??? ");
            product71.setStatus("ACTIVE");
            product71.setCategoryId(2); //1
            list.add(product71);

            Product product72 = new Product();
            product72.setId(73);
            product72.setName("D??u t??y M??? ");
            product72.setPrice(36000);
            product72.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271929/Album%20Fruit/dau-tay-my-dalat_tmgfxk.jpg");
            product72.setQuantity(100);
            product72.setDescription("????y l?? qu??? d??u t??y M??? ");
            product72.setDetail("M??? ");
            product72.setStatus("ACTIVE");
            product72.setCategoryId(4); //1
            list.add(product72);

            Product product73 = new Product();
            product73.setId(74);
            product73.setName("D??u t??y ???? L???t ");
            product73.setPrice(36000);
            product73.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271929/Album%20Fruit/dau-tay-da-lat_zaaciz.jpg");
            product73.setQuantity(100);
            product73.setDescription("????y l?? qu??? d??u t??y ???? L???t ");
            product73.setDetail("???? L???t ");
            product73.setStatus("ACTIVE");
            product73.setCategoryId(2); //1
            list.add(product73);

            Product product74 = new Product();
            product74.setId(75);
            product74.setName("D??u t??y ???? L???t ");
            product74.setPrice(36000);
            product74.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271929/Album%20Fruit/dau-tay-da-lat_zaaciz.jpg");
            product74.setQuantity(100);
            product74.setDescription("????y l?? qu??? d??u t??y ???? L???t ");
            product74.setDetail("???? L???t ");
            product74.setStatus("ACTIVE");
            product74.setCategoryId(4); //2
            list.add(product74);

            Product product75 = new Product();
            product75.setId(76);
            product75.setName("D??u t??y B???ch Tuy???t ");
            product75.setPrice(36000);
            product75.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/dau-anh-dao-nhat_qwmsm1.jpg");
            product75.setQuantity(100);
            product75.setDescription("????y l?? qu??? d??u t??y B???ch Tuy???t ");
            product75.setDetail("Nh???t B???n ");
            product75.setStatus("ACTIVE");
            product75.setCategoryId(1); //4
            list.add(product75);

            Product product76 = new Product();
            product76.setId(77);
            product76.setName("D??u t??y B???ch Tuy???t ");
            product76.setPrice(36000);
            product76.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271928/Album%20Fruit/dau-anh-dao-nhat_qwmsm1.jpg");
            product76.setQuantity(100);
            product76.setDescription("????y l?? qu??? d??u t??y B???ch Tuy???t ");
            product76.setDetail("Nh???t B???n ");
            product76.setStatus("ACTIVE");
            product76.setCategoryId(2); //4
            list.add(product76);

            Product product77 = new Product();
            product77.setId(78);
            product77.setName("D??u T???m ");
            product77.setPrice(36000);
            product77.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271929/Album%20Fruit/dau-tam-5_a9rykw.jpg    ");
            product77.setQuantity(100);
            product77.setDescription("????y l?? qu??? d??u T???m ");
            product77.setDetail("???? L???t ");
            product77.setStatus("ACTIVE");
            product77.setCategoryId(1); //4
            list.add(product77);

            Product product78 = new Product();
            product78.setId(79);
            product78.setName("D??u t??y ???? L???t ");
            product78.setPrice(36000);
            product78.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271929/Album%20Fruit/dau-tay-da-lat_zaaciz.jpg");
            product78.setQuantity(100);
            product78.setDescription("????y l?? qu??? d??u t??y ???? L???t ");
            product78.setDetail("???? L???t ");
            product78.setStatus("ACTIVE");
            product78.setCategoryId(2);
            list.add(product78);

            Product product79 = new Product();
            product79.setId(80);
            product79.setName("Qu??? B?? d??i");
            product79.setPrice(36000);
            product79.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1652271931/Album%20Fruit/qua-bo-034_kk2wj4.jpg");
            product79.setQuantity(100);
            product79.setDescription("????y l?? qu??? b?? d??i");
            product79.setDetail("???? L???t");
            product79.setStatus("ACTIVE");
            product79.setCategoryId(1);
            list.add(product79);


            productRepository.saveAll(list);
        }
    }

    private void loadCategory(){
        if(categoryRepository.count() == 0){
            List<Category> list = new ArrayList<>();
            Category category = new Category();
            category.setId(1);
            category.setName("M??a Xu??n");
            category.setDescription("????y l?? m??a xu??n");
            category.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1653054064/Categories/wallpaper_springjpg_dtoe0v.jpg");
            category.setStatus("ACTIVE");
            list.add(category);

            Category category1 = new Category();
            category1.setId(2);
            category1.setName("M??a H??");
            category1.setDescription("????y l?? m??a h??");
            category1.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1653054064/Categories/wallpaper_summer_umt3ac.jpg");
            category1.setStatus("ACTIVE");
            list.add(category1);

            Category category2 = new Category();
            category2.setId(3);
            category2.setName("M??a Thu");
            category2.setDescription("????y l?? m??a thu");
            category2.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1653054064/Categories/wallpaper_fall1_espvci.jpg");
            category2.setStatus("ACTIVE");
            list.add(category2);

            Category category3 = new Category();
            category3.setId(4);
            category3.setName("M??a ????ng");
            category3.setDescription("????y l?? m??a ????ng");
            category3.setThumbnail("https://res.cloudinary.com/sem4-fruitapp/image/upload/v1653054064/Categories/wallpaper_winter_bcalyz.jpg");
            category3.setStatus("ACTIVE");
            list.add(category3);

            categoryRepository.saveAll(list);
        }
    }

    private void loadOrder(){
        if(orderDetailRepository.count() == 0){
            List<Order> orders = new ArrayList<>();
            List<OrderDetail> orderDetails = new ArrayList<>();
            boolean existProduct = false;

            for (int i = 1; i < 60; i++) {
                Order order = new Order();
                double total = 0;
                int accountId = faker.number().numberBetween(1,5);
                int orderDetailNumber = faker.number().numberBetween(1, 5);

                System.out.println(order.getId());
                order.setStatus(1);
                order.setCreatedAt(LocalDate.now());
                order.setUpdatedAt(LocalDate.now());
                order.setCheckout(faker.random().nextBoolean());
                order.setIsRemoved(1);
                order.setShipName(faker.name().fullName());
                order.setShipPhone(faker.phoneNumber().phoneNumber());
                order.setShipNote("Giao h??ng nhanh gi??p em!");
                order.setShipAddress("H?? N???i");
                Optional<Account> account = accountRepository.findById(accountId);
                order.setAccount(account.get());
                order.setAccountId(account.get().getId());
                for (int j = 0; j < orderDetailNumber; j++) {
                    int productId = faker.number().numberBetween(1, 50);
                    for (OrderDetail od:
                            orderDetails) {
                        // check c?? tr??ng product kh??ng?C?? th?? break
                        if(od.getProduct().getId() == productId && od.getOrder().getAccount().getId() == accountId){
                            existProduct = true;
                            break;
                        }
                    }
                    //N???u th???ng tr??n c?? th?? exitsProduct = true
                    if(existProduct){
                        j--;
                        existProduct = false;
                        continue;
                    }
                    OrderDetail orderDetail = new OrderDetail();
                    Product product = productRepository.findById(productId).get();
                    orderDetail.setProduct(product);
                    int quantity = faker.number().numberBetween(1,5);
                    orderDetail.setOrder(order);
                    orderDetail.setQuantity(quantity);

                    orderDetail.setProductName(product.getName());
                    orderDetail.setThumbnailProduct(product.getThumbnail());

                    double unitPrice = product.getPrice();
                    orderDetail.setUnitPrice(unitPrice);
                    total += unitPrice * quantity;
                    orderDetails.add(orderDetail);

                }
                order.setTotalPrice(total);
                orders.add(order);
                orderRepository.saveAll(orders);
                orderDetailRepository.saveAll(orderDetails);
            }
        }
        }
}
