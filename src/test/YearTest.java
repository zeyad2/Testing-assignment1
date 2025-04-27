    package test;

    import org.jfree.data.time.Year;
    import org.junit.Assert;
    import org.junit.Test;

    import java.util.Calendar;
    import java.util.Date;
    import java.util.TimeZone;

    import static org.junit.Assert.*;

    public class YearTest {
        Year year;

        private void arrange() {
            year = new Year();
        }

        @Test
        public void testYearDefaultCtor() {
            arrange();
            assertEquals(2025, year.getYear());
        }

        @Test
        public void testNext(){
            arrange();

            System.out.println( year.next());

            assertEquals(new Year(2026),year.next());


        }


        @Test
        public void testPrevious(){
            arrange();

            assertEquals(new Year(2024),year.previous());


        }



        @Test
        public void testCompareToPrev(){
            arrange();
            Year prevYear= new Year(2023);

            assertEquals(2,year.compareTo(prevYear));

        }

        @Test
        public void testCompareToAfter(){
            arrange();
            Year futureYear = new Year(2028);
            assertEquals(-3,year.compareTo(futureYear));
        }


        @Test
        public void testNotEquals(){
            arrange();

            Year comparedYear= new Year(2300);

            assertFalse(year.equals(comparedYear));
        }


        @Test
        public void testEquals(){
            arrange();
            Year comparedYear= new Year(2025);

            assertTrue(year.equals(comparedYear));
        }

        @Test
        public void testGetFirstMillisecond() {

            arrange();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2025);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            assertEquals(calendar.getTimeInMillis(), year.getFirstMillisecond());


        }

        @Test
        public void testGetLastMillisecond() {

            arrange();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2025);
            calendar.set(Calendar.MONTH, 11);
            calendar.set(Calendar.DAY_OF_MONTH, 31);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);

            assertEquals(calendar.getTimeInMillis(), year.getLastMillisecond());


        }


        @Test
        public void testGetSerialIndex(){
            arrange();

            assertEquals(year.getYear(),year.getSerialIndex());

        }




        @Test
        public void testToString(){
            arrange();
            String StringYear = "2025";
            assertEquals(StringYear, year.toString());
        }


        @Test
        public void testHashCode(){
            arrange();
            int hashCode= 2654;
            assertEquals(hashCode,year.hashCode());
        }


        @Test
        public void testParseYear(){
            arrange();
            String stringYear="2022";
            assertEquals(new Year(2022),year.parseYear(stringYear));
        }


        @Test
        public void testPreviousFrom100() {
            Year minYear = new Year(100);
            assertEquals(new Year(99), minYear.previous());
        }


            @Test
            public void testParseInvalidYear() {
                arrange();
                String stringYear = "Zeyad";
                assertNull(year.parseYear(stringYear));
            }

        @Test
        public void testYearDateTimezoneCtor() {
            TimeZone zone = TimeZone.getTimeZone("America/New_York");
            Calendar calendar = Calendar.getInstance(zone);
            calendar.set(2021, 11, 31);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            Date date = calendar.getTime();
            this.year = new Year(date);
            Assert.assertEquals(2021L, (long)this.year.getYear());
        }
        @Test
        public void testYearDateCtor() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2022, 5, 15);
            Date date = calendar.getTime();
            this.year = new Year(date);
            Assert.assertEquals(2022L, (long)this.year.getYear());
        }

        @Test(expected = IllegalArgumentException.class)
        public void testNextFromMax() {
            Year maxYear = new Year(10000);
            maxYear.next();
        }



    }
