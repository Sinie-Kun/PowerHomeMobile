package iut.dam.powerhome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import iut.dam.powerhome.model.Appliance;
import iut.dam.powerhome.model.Habitat;

public class HabitatsFragment extends Fragment {

    public HabitatsFragment() {}

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        requireActivity().setTitle("Liste des habitats");

        View rootView = inflater.inflate(R.layout.fragment_habitats, container, false);

        // Handle edge insets if needed
        ViewCompat.setOnApplyWindowInsetsListener(rootView.findViewById(R.id.listView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<Appliance> appareilsGaetanL = new ArrayList<Appliance>();
        appareilsGaetanL.add(new Appliance(1, "Lave-linge", "ll-9000", 1200));
        appareilsGaetanL.add(new Appliance(2, "Aspirateur", "Aspir-55", 300));
        appareilsGaetanL.add(new Appliance(2, "Climatiseur", "Clim-738", 2000));
        appareilsGaetanL.add(new Appliance(2, "Fer", "Fer-682", 1800));

        List<Appliance> appareilsCedricB = new ArrayList<Appliance>();
        appareilsCedricB.add(new Appliance(1, "Lave-linge", "ll-9000", 1200));

        List<Appliance> appareilsGaylordT = new ArrayList<Appliance>();
        appareilsGaylordT.add(new Appliance(2, "Aspirateur", "Aspir-55", 300));
        appareilsGaylordT.add(new Appliance(2, "Fer", "Fer-682", 1800));

        List<Appliance> appareilsAdamJ = new ArrayList<Appliance>();
        appareilsAdamJ.add(new Appliance(1, "Lave-linge", "ll-9000", 1200));
        appareilsAdamJ.add(new Appliance(2, "Aspirateur", "Aspir-55", 300));
        appareilsAdamJ.add(new Appliance(2, "Fer", "Fer-682", 1800));

        List<Appliance> appareilsAbelF = new ArrayList<Appliance>();
        appareilsAbelF.add(new Appliance(2, "Aspirateur", "Aspir-55", 300));

        List<Habitat> ListeHabitats = new ArrayList<>();
        ListeHabitats.add(new Habitat(1,"Gaëtan Leclair", 1, 33, appareilsGaetanL));
        ListeHabitats.add(new Habitat(2,"Cédric Boudet", 1, 33, appareilsCedricB));
        ListeHabitats.add(new Habitat(3,"Gaylord Thibodeaux", 2, 33, appareilsGaylordT));
        ListeHabitats.add(new Habitat(4,"Adam Jacquinot", 3, 33, appareilsAdamJ));
        ListeHabitats.add(new Habitat(5,"Abel Fresnel", 3, 33, appareilsAbelF));



        ListView listView = rootView.findViewById(R.id.listView);
        HabitatAdapter adapter = new HabitatAdapter(requireContext(), ListeHabitats);
        listView.setAdapter(adapter);

        return rootView;
    }
}
