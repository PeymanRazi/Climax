package puresoft.org.climax.viewes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    // tab titles
    private String[] tabTitles = new String[]{"input", "output", "W h","stf", "prn","noty"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new Input();
            case 1:
                return new Output();
            case 2:
                return new Warehouse();
            case 3:
                return new Stuffs();
            case 4:
                return new Persons();
            case 5:
                return new Notification();
            default:
                return null;

        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }




    @Override
    public int getCount() {
        return 6;
    }
}