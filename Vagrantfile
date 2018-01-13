Vagrant.configure("2") do |config|
    # Box configuration
    config.vm.box = "ubuntu/trusty64"
    config.vm.box_check_update = false
    config.vm.hostname = 'codelab'
    config.vm.provider "virtualbox" do |vb|
        vb.memory = 1500 #MB
        vb.cpus = 1
    end

    # Forward ports for postgres
    config.vm.network "forwarded_port", guest: 5432, host: 5432

    # Install software
    config.vm.provision "shell", inline: <<-INSTALL_SOFTWARE
    sudo apt-get --yes --force-yes update
    
    # Postgresql
    sudo apt-get --yes --force-yes install postgresql postgresql-contrib
    sudo cp /vagrant/config/postgres/* /etc/postgresql/9.3/main/
    sudo service postgresql restart
    
    INSTALL_SOFTWARE
    
    # Initialize database
    config.vm.provision "shell", inline: <<-INIT_DB
    sudo -u postgres createdb codelab
    INIT_DB
end
