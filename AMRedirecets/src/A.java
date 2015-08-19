
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class A {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	int i = 0;
	public String[] a = { "campbellsamherst.ca", "campbellsamherst.ca/About-Us/",
			"campbellsamherst.ca/About-Us/Accessibility/", "campbellsamherst.ca/About-Us/Amenities/",
			"campbellsamherst.ca/About-Us/Careers_at_Arbor/", "campbellsamherst.ca/About-Us/Community-Involvement/",
			"campbellsamherst.ca/About-Us/FAQs/", "campbellsamherst.ca/About-Us/Our-Staff/",
			"campbellsamherst.ca/About-Us/Overview/", "campbellsamherst.ca/About-Us/Special-Events/",
			"campbellsamherst.ca/Privacy-Policy/", "www.campbellsamherst.ca/AfterCare/Coping-With-Grief/",
			"www.campbellsamherst.ca/Cemetery/Order-Flowers/", "www.campbellsamherst.ca/Services-Schedule/default.asp",
			"www.campbellsamherst.ca/Site-Map/default.asp", "www.campbellsamherst.ca/Privacy-Policy",
			"campbellsamherst.ca", "evergreenmemorial.ca", "highlandfuneralhome.ca", "janissefuneralhome.ca",
			"kellyfh.ca/Barrhaven", "kellyfh.ca/Carling", "kellyfh.ca/Kanata", "kellyfh.ca/Orleans",
			"kellyfh.ca/Somerset", "kellyfh.ca/Walkley", "leydens.com/Calgary", "leydens.com/Claresholm",
			"sandsfuneral.com", "victoriagreenlawn.ca", "memorialarbor.ca",
			"blog.arbormemorial.com/blog/arbor_memorial_national_contest_winner",
			"blog.arbormemorial.com/blog/articles/5-things-you-may-not-know-about-cremation",
			"blog.arbormemorial.com/blog/articles/arbor-alliance-partner-spotlight-the-royal-canadian-legion",
			"blog.arbormemorial.com/blog/articles/ask-the-experts",
			"blog.arbormemorial.com/blog/articles/celebrate-their-memory-at-a-seasonal-service",
			"blog.arbormemorial.com/blog/articles/ching-ming-honouring-a-proud-chinese-heritage",
			"blog.arbormemorial.com/blog/articles/coping-with-holiday-grief",
			"blog.arbormemorial.com/blog/articles/creating-online-tributes",
			"blog.arbormemorial.com/blog/articles/four-ways-to-reduce-funeral-stress",
			"blog.arbormemorial.com/blog/articles/free-grief-support-sessions",
			"blog.arbormemorial.com/blog/articles/how-to-plan-a-green-funeral",
			"blog.arbormemorial.com/blog/articles/how-to-talk-to-your-parents-about-their-funeral-plans",
			"blog.arbormemorial.com/blog/articles/how-to-write-a-condolence-note",
			"blog.arbormemorial.com/blog/articles/how-to-write-an-obituary",
			"blog.arbormemorial.com/blog/articles/its-new-years-resolution-time",
			"blog.arbormemorial.com/blog/articles/its-yard-sale-season",
			"blog.arbormemorial.com/blog/articles/join-us-for-beautification-day",
			"blog.arbormemorial.com/blog/articles/mourning-mandela",
			"blog.arbormemorial.com/blog/articles/nature-walks-offer-peaceful-memorialization-options",
			"blog.arbormemorial.com/blog/articles/pre-plan-for-peace-of-mind",
			"blog.arbormemorial.com/blog/articles/tax-tips-for-seniors",
			"blog.arbormemorial.com/blog/articles/the-benefits-of-having-a-will",
			"blog.arbormemorial.com/blog/articles/the-importance-of-remembrance",
			"blog.arbormemorial.com/blog/articles/what-is-memorialization",
			"blog.arbormemorial.com/blog/articles/when-the-world-mourns-tragedy-together",
			"blog.arbormemorial.com/blog/five-reasons-to-pre-plan-your-final-arrangements",
			"blog.arbormemorial.com/blog/four-funeral-myths",
			"blog.arbormemorial.com/blog/helping-you-cope-grief-support-services-and-resources",
			"blog.arbormemorial.com/blog/honour-moms-memory-on-mothers-day",
			"blog.arbormemorial.com/blog/how-to-talk-to-your-parents-about-their-funeral-plans",
			"blog.arbormemorial.com/blog/introducing-virtual-memorials",
			"blog.arbormemorial.com/blog/losing-robin-williams-larger-than-life-even-in-death",
			"blog.arbormemorial.com/blog/managing-your-grief-on-valentines-day",
			"blog.arbormemorial.com/blog/nature-walks-provide-a-beautiful-outdoor-memorial",
			"blog.arbormemorial.com/blog/planning-for-life’s-final-transition",
			"blog.arbormemorial.com/blog/snow-shovelling-safety-tips", "blog.arbormemorial.com/blog/topic/cremation",
			"blog.arbormemorial.com/blog/topic/grief", "blog.arbormemorial.com/blog/topic/memorials",
			"blog.arbormemorial.com/blog/topic/mourning", "blog.arbormemorial.com/blog/topic/pre-planning",
			"blog.arbormemorial.com/blog/transfer-privileges-a-valuable-benefitbrwhen-pre-planning",
			"blog.arbormemorial.com/blog/when-our-nation-mourns",
			"www.arbormemorial.com.vs5.korax.net/articles/celebrate-their-memory-at-a-seasonal-service/",
			"www.arbormemorial.com.vs5.korax.net/locations", "www.arbormemorial.com/about-arbor/arbor-alliances/",
			"www.arbormemorial.com/about-arbor/arbor-foundation/", "www.arbormemorial.com/about-arbor/careers/",
			"www.arbormemorial.com/about-arbor/careers/current-positions/",
			"www.arbormemorial.com/about-arbor/careers/working-at-arbor/",
			"www.arbormemorial.com/about-arbor/community-involvement/",
			"www.arbormemorial.com/about-arbor/community-involvement/bereaved-families-of-ontario-toronto/",
			"www.arbormemorial.com/about-arbor/community-involvement/childrens-wish-foundation/",
			"www.arbormemorial.com/about-arbor/community-involvement/kids-help-phone/",
			"www.arbormemorial.com/about-arbor/community-involvement/red-cross-flood-relief/",
			"www.arbormemorial.com/about-arbor/contact-us", "www.arbormemorial.com/about-arbor/contact-us/",
			"www.arbormemorial.com/about-arbor/mission-statement/", "www.arbormemorial.com/about-arbor/timeline/",
			"www.arbormemorial.com/about-us/", "www.arbormemorial.com/about-us/arbor-alliances/",
			"www.arbormemorial.com/about-us/arbor-foundation/", "www.arbormemorial.com/about-us/our-history/",
			"www.arbormemorial.com/articles/5-things-you-may-not-know-about-cremation/",
			"www.arbormemorial.com/articles/how-to-write-a-condolence-note/",
			"www.arbormemorial.com/articles/how-to-write-an-obituary/",
			"www.arbormemorial.com/articles/join-us-for-beautification-day/", "www.arbormemorial.com/careers/",
			"www.arbormemorial.com/careers/current-positions/", "www.arbormemorial.com/careers/working-at-arbor/",
			"www.arbormemorial.com/category/currentpositions/", "www.arbormemorial.com/fr/about-arbor/contact-us/",
			"www.arbormemorial.com/fr/about-us/", "www.arbormemorial.com/fr/about-us/arbor-alliances/",
			"www.arbormemorial.com/fr/about-us/arbor-foundation/",
			"www.arbormemorial.com/fr/about-us/community-involvement/",
			"www.arbormemorial.com/fr/about-us/community-involvement/bereaved-families-of-ontario-toronto/",
			"www.arbormemorial.com/fr/about-us/community-involvement/canadian-cancer-society/",
			"www.arbormemorial.com/fr/about-us/community-involvement/childrens-wish-foundation/",
			"www.arbormemorial.com/fr/about-us/community-involvement/kids-help-phone/",
			"www.arbormemorial.com/fr/about-us/community-involvement/love-respect-conference-sponsor/",
			"www.arbormemorial.com/fr/about-us/community-involvement/red-cross-flood-relief/",
			"www.arbormemorial.com/fr/about-us/community-involvement/surrey-hospice-society/",
			"www.arbormemorial.com/fr/about-us/community-involvement/typhoon-haiyan-relief/",
			"www.arbormemorial.com/fr/about-us/contact_details/", "www.arbormemorial.com/fr/about-us/contact-us/",
			"www.arbormemorial.com/fr/about-us/our-history/", "www.arbormemorial.com/fr/about-us/our-philosophy/",
			"www.arbormemorial.com/fr/about-us/press-centre/",
			"www.arbormemorial.com/fr/articles/5-things-you-may-not-know-about-cremation/",
			"www.arbormemorial.com/fr/articles/how-to-write-a-condolence-note/",
			"www.arbormemorial.com/fr/articles/how-to-write-an-obituary/",
			"www.arbormemorial.com/fr/articles/join-us-for-beautification-day/", "www.arbormemorial.com/fr/careers/",
			"www.arbormemorial.com/fr/careers/current-positions/", "www.arbormemorial.com/fr/careers/working-at-arbor/",
			"www.arbormemorial.com/fr/category/currentpositions/", "www.arbormemorial.com/fr/home-page/site-map/",
			"www.arbormemorial.com/fr/job-postings/apprentice-funeral-director-crematorium-operator/",
			"www.arbormemorial.com/fr/job-postings/digital-marketing-specialist/",
			"www.arbormemorial.com/fr/job-postings/full-time-groundsperson-2/",
			"www.arbormemorial.com/fr/job-postings/full-time-groundsperson-3/",
			"www.arbormemorial.com/fr/job-postings/full-time-groundsperson/",
			"www.arbormemorial.com/fr/job-postings/funeral-director-12/",
			"www.arbormemorial.com/fr/job-postings/funeral-director-13/",
			"www.arbormemorial.com/fr/job-postings/funeral-director-14/",
			"www.arbormemorial.com/fr/job-postings/funeral-director-scott-funeral-home-brampton/",
			"www.arbormemorial.com/fr/job-postings/groundsperson-full-time/",
			"www.arbormemorial.com/fr/job-postings/human-resources-advisor-one-year-contract/",
			"www.arbormemorial.com/fr/job-postings/infrastructure-analyst-information-services/",
			"www.arbormemorial.com/fr/job-postings/junior-trust-accountant/",
			"www.arbormemorial.com/fr/job-postings/licensed-funeral-director-mount-lawn-funeral-home-cemetery/",
			"www.arbormemorial.com/fr/job-postings/office-administrator-2/",
			"www.arbormemorial.com/fr/job-postings/para-funeral-assistant-hostessreceptionist-part-time-on-call/",
			"www.arbormemorial.com/fr/job-postings/part-time-building-maintenance-2/",
			"www.arbormemorial.com/fr/job-postings/part-time-building-maintenance/",
			"www.arbormemorial.com/fr/job-postings/payroll-administrator-3/",
			"www.arbormemorial.com/fr/job-postings/programmeranalyst-12-month-contract/",
			"www.arbormemorial.com/fr/job-postings/sales-representative-2/",
			"www.arbormemorial.com/fr/job-postings/sales-representative-4/",
			"www.arbormemorial.com/fr/job-postings/sales-representative-5/",
			"www.arbormemorial.com/fr/job-postings/sales-representative-6/",
			"www.arbormemorial.com/fr/media-events/events/", "www.arbormemorial.com/fr/media-events/newsletters/",
			"www.arbormemorial.com/fr/media-events/site-tours/",
			"www.arbormemorial.com/fr/media-events/videos-and-testimonials/", "www.arbormemorial.com/fr/tag/alliances/",
			"www.arbormemorial.com/fr/tag/arbor-alliances/", "www.arbormemorial.com/fr/tag/arbor-memorial/",
			"www.arbormemorial.com/fr/tag/arbor-memorial/page/2/",
			"www.arbormemorial.com/fr/tag/arbor-memorial/page/3/", "www.arbormemorial.com/fr/tag/beautification-day/",
			"www.arbormemorial.com/fr/tag/butterfly-release/", "www.arbormemorial.com/fr/tag/canadian-cancer-society/",
			"www.arbormemorial.com/fr/tag/careers/", "www.arbormemorial.com/fr/tag/cemetery-plot/",
			"www.arbormemorial.com/fr/tag/ching-ming/", "www.arbormemorial.com/fr/tag/christmas-service/",
			"www.arbormemorial.com/fr/tag/condolence-card/", "www.arbormemorial.com/fr/tag/condolence-note/",
			"www.arbormemorial.com/fr/tag/condolence/", "www.arbormemorial.com/fr/tag/coping-with-grief/",
			"www.arbormemorial.com/fr/tag/cremated-remains/", "www.arbormemorial.com/fr/tag/cremation-jewlery/",
			"www.arbormemorial.com/fr/tag/cremation-rocks/", "www.arbormemorial.com/fr/tag/cremation/",
			"www.arbormemorial.com/fr/tag/current-positions/", "www.arbormemorial.com/fr/tag/death-cafe/",
			"www.arbormemorial.com/fr/tag/dodsworth-brown-ancaster/", "www.arbormemorial.com/fr/tag/donations/",
			"www.arbormemorial.com/fr/tag/envirnomentally-friendly-funeral/",
			"www.arbormemorial.com/fr/tag/environmentally-friendly-burial/",
			"www.arbormemorial.com/fr/tag/environmentally-friendly-cemetery/",
			"www.arbormemorial.com/fr/tag/estate-planning/", "www.arbormemorial.com/fr/tag/events-2/",
			"www.arbormemorial.com/fr/tag/executor/", "www.arbormemorial.com/fr/tag/family/",
			"www.arbormemorial.com/fr/tag/fonhtill/", "www.arbormemorial.com/fr/tag/funeral-director-jobs/",
			"www.arbormemorial.com/fr/tag/funeral-planning/", "www.arbormemorial.com/fr/tag/funeral-pre-planning/",
			"www.arbormemorial.com/fr/tag/funeral-service/", "www.arbormemorial.com/fr/tag/funerals/",
			"www.arbormemorial.com/fr/tag/garage-sale/", "www.arbormemorial.com/fr/tag/green-burial/",
			"www.arbormemorial.com/fr/tag/green-cemetery/", "www.arbormemorial.com/fr/tag/green-funeral/",
			"www.arbormemorial.com/fr/tag/green-funerals/", "www.arbormemorial.com/fr/tag/grief-counselling/",
			"www.arbormemorial.com/fr/tag/grief-support/", "www.arbormemorial.com/fr/tag/grief/",
			"www.arbormemorial.com/fr/tag/healing/", "www.arbormemorial.com/fr/tag/income-splitting-for-seniors/",
			"www.arbormemorial.com/fr/tag/income-tax/", "www.arbormemorial.com/fr/tag/jobs/",
			"www.arbormemorial.com/fr/tag/kelly-funeral-homes/",
			"www.arbormemorial.com/fr/tag/last-will-and-testament/", "www.arbormemorial.com/fr/tag/legal-will-kit/",
			"www.arbormemorial.com/fr/tag/legal-will/", "www.arbormemorial.com/fr/tag/legion/",
			"www.arbormemorial.com/fr/tag/mceachnie-funera-home/",
			"www.arbormemorial.com/fr/tag/memorial-funeral-home/", "www.arbormemorial.com/fr/tag/memorial-gardens/",
			"www.arbormemorial.com/fr/tag/memorial-service/", "www.arbormemorial.com/fr/tag/memorial-service/page/2/",
			"www.arbormemorial.com/fr/tag/memorials/", "www.arbormemorial.com/fr/tag/memorials/page/2/",
			"www.arbormemorial.com/fr/tag/monument/", "www.arbormemorial.com/fr/tag/monuments/",
			"www.arbormemorial.com/fr/tag/mount-lawn-memorial-gardens/", "www.arbormemorial.com/fr/tag/mourning/",
			"www.arbormemorial.com/fr/tag/niagaras-biggest-yard-sale/", "www.arbormemorial.com/fr/tag/niche/",
			"www.arbormemorial.com/fr/tag/obituary/" };
	int acount = a.length;

	@Before
	public void setUp() throws Exception {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void testA() throws Exception {

		System.out.println(acount);

		try {

			do {
				double n = i;
				double d = acount;
				double percent = (n / d);
				double percent1 = percent * 100;
				System.out.println(i);
				System.out.println(percent1 + "%");
				System.out.println(a[i]);
				String url = a[i].trim();
				if (a[i].startsWith("http://") == false) {
					driver.get("http://" + url);
				} else {
					driver.get(url);
				}
				Thread.sleep(2000);
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				if (a[i].endsWith("/") == true) {

					String https = a[i].replace("/", "");
					String httpsa = https.replace(".", "");
					String httpsba = httpsa.replace("-", "");
					String filename = "C:\\Users\\kevin.anderson\\Desktop\\New folder\\" + httpsba + ".png";
					System.out.println(filename);
					FileUtils.copyFile(scrFile, new File(filename));
				} else {
					FileUtils.copyFile(scrFile,
							new File("C:\\Users\\kevin.anderson\\Desktop\\New folder\\" + a[i] + ".png"));
				}
				i++;
			} while (i <= acount);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
