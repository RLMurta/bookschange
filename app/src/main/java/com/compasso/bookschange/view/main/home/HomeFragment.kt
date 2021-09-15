package com.compasso.bookschange.view.main.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.compasso.bookschange.databinding.FragmentHomeBinding
import com.compasso.bookschange.model.Constants.Companion.DETACHMENT_LIST_DATABASE
import com.compasso.bookschange.model.Constants.Companion.DETACHMENT_LIST_OPTION
import com.compasso.bookschange.model.Constants.Companion.WISHLIST_DATABASE
import com.compasso.bookschange.model.Constants.Companion.WISHLIST_OPTION
import com.compasso.bookschange.model.main.home.GridSpacingItemDecoration
import com.compasso.bookschange.model.main.home.HomeFragmentAdapter
import com.compasso.bookschange.model.room.AppDatabase
import com.compasso.bookschange.model.room.Book
import com.compasso.bookschange.viewModel.LoadingDialog
import com.compasso.bookschange.viewModel.ViewModelFactory
import com.compasso.bookschange.viewModel.main.home.HomeViewModel

class HomeFragment : Fragment(), HomeFragmentAdapter.Buttons {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var wishListDb: AppDatabase
    private lateinit var detachmentListDb: AppDatabase
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        addSpacingToRecyclerView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog()
        isPermissionAccepted()
        instantiateVariables()
        observatory()

        viewModel.fetchMyBooks(wishListDb, WISHLIST_OPTION)
        viewModel.fetchMyBooks(detachmentListDb, DETACHMENT_LIST_OPTION)
    }

    private fun addSpacingToRecyclerView() {
        binding.wishlistRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                3,
                50,
                true
            )
        )

        binding.detachmentRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                3,
                50,
                true
            )
        )
    }

    private fun isPermissionAccepted() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                (requireContext()),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        }
    }

    private fun instantiateVariables() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        ).get(HomeViewModel::class.java)

        wishListDb = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, WISHLIST_DATABASE
        ).build()
        detachmentListDb = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, DETACHMENT_LIST_DATABASE
        ).build()
    }

    private fun observatory() {
        viewModel.myBookWishlist.observe(viewLifecycleOwner, { books ->
            bindingRecyclerViewAtributes(books, WISHLIST_OPTION)
        })

        viewModel.myBookDetachmentlist.observe(viewLifecycleOwner, { books ->
            bindingRecyclerViewAtributes(books, DETACHMENT_LIST_OPTION)
        })

        viewModel.startLoading.observe(viewLifecycleOwner, {
            loadingDialog.startLoadingDialog(requireActivity())
        })

        viewModel.stopLoading.observe(viewLifecycleOwner, {
            loadingDialog.dismissDialog()
        })
    }

    private fun bindingRecyclerViewAtributes(books: List<Book>, option: Int) {
        val adapter = HomeFragmentAdapter(books, this, option)
        when (option) {
            WISHLIST_OPTION -> {
                binding.wishlistRecyclerView.layoutManager = GridLayoutManager(context, 3)
                binding.wishlistRecyclerView.adapter = adapter
            }
            DETACHMENT_LIST_OPTION -> {
                binding.detachmentRecyclerView.layoutManager = GridLayoutManager(context, 3)
                binding.detachmentRecyclerView.adapter = adapter
            }
        }
    }

    override fun onAddBookButtonClicked(listOption: Int) {
        val databaseName = getDatabaseName(listOption)
        val action =
            HomeFragmentDirections.actionHomeFragmentToBookSearchFragment(databaseName)
        view?.findNavController()?.navigate(action)
    }

    override fun onRemoveBookButtonClicked(listOption: Int, position: Int) {
        viewModel.removeBook(
            Room.databaseBuilder(
                requireContext(),
                AppDatabase::class.java, getDatabaseName(listOption)
            ).build(),
            listOption, position
        )
    }

    private fun getDatabaseName(listOption: Int): String {
        when (listOption) {
            WISHLIST_OPTION -> {
                return WISHLIST_DATABASE
            }
            else -> {
                return DETACHMENT_LIST_DATABASE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
